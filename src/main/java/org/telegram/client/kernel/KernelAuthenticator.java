package org.telegram.client.kernel;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.telegram.api.TLConfig;
import org.telegram.api.auth.base.authorization.TLAbsAuthAuthorization;
import org.telegram.api.auth.base.sentcode.TLSentCode;
import org.telegram.api.auth.functions.TLRequestAuthImportBotAuthorization;
import org.telegram.api.auth.functions.TLRequestAuthLogOut;
import org.telegram.api.auth.functions.TLRequestAuthSendCode;
import org.telegram.api.auth.functions.TLRequestAuthSignIn;
import org.telegram.api.auth.functions.TLRequestAuthSignUp;
import org.telegram.api.help.functions.TLRequestHelpGetConfig;
import org.telegram.api.updates.functions.TLRequestUpdatesGetState;
import org.telegram.MyTLAppConfiguration;
import org.telegram.api.TLCodeSettings;
import org.telegram.utils.RpcException;
import org.telegram.utils.BotLogger;
import org.telegram.client.structure.LoginStatus;

public class KernelAuthenticator {

    private static final String LOGTAG = "[KernelAuthenticator]";
    private static final int TIME_UNTIL_CALLING_USER = 60000;
    private static final int ERROR_303 = 303;

    private MyTLAppConfiguration config;
    private Timer loginTimer = new Timer();

    public KernelAuthenticator() { }

    public void build(MyTLAppConfiguration config) {
        this.config = config;
    }

    public LoginStatus start() {
        return this.config.isBot() ? this.loginBot() : this.login();
    }

    public boolean logOut() {
        boolean result;
        try {
            if (this.config.getApiState().isAuthenticated()) {
                // Logout previous
                this.config.getKernelCommunicationService().doRpcCallSync(new TLRequestAuthLogOut());
                // Reset previous stored credentials
                this.config.getApiState().resetAuth();
            }
            this.config.setRegistered(false);
            result = true;
        } catch (ExecutionException | RpcException e) {
            BotLogger.error(LOGTAG, e);
            result = false;
        }

        return result;
    }

    public int getCurrentUserId() {
        return this.config.getApiState().getUserId();
    }

    public boolean setAuthCode(String code) {
        boolean result = false;
        try {
            if (this.config.getApiHash().trim().isEmpty()) {
                result = false;
            } else {
                final TLAbsAuthAuthorization authorization;
                if (this.config.isRegistered()) {
                    final TLRequestAuthSignIn tlRequestAuthSignIn = getSignInRequest(code);
                    authorization = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSignIn);
                } else {
                    final TLRequestAuthSignUp tlRequestAuthSignUp = getSignUpRequest();
                    authorization = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSignUp);
                }
                if (authorization != null) {
                    this.config.setRegistered(true);
                    this.config.getApiState().doAuth(authorization);
                    BotLogger.info(LOGTAG,"Activation complete as #" + this.config.getApiState().getObject().getUid());
                    this.config.getApi().doRpcCall(new TLRequestUpdatesGetState());
                    BotLogger.info(LOGTAG,"Loaded initial state");
                    resetTimer();
                    result = true;
                }
            }
        } catch (IOException | TimeoutException e) {
            BotLogger.error(LOGTAG, e);
            result = false;
        }

        return result;
    }

    //region Private helpers

    private LoginStatus login() {
        LoginStatus result;
        try {
            if (this.config.getApiState().isAuthenticated()) {
                BotLogger.info(LOGTAG,"Found Auth file");
                config.setRegistered(true);
                result = LoginStatus.ALREADYLOGGED;
            } else {
                TLSentCode sentCode = null;
                try {
                    final TLConfig tlConfig = this.config.getApi().doRpcCallNonAuth(new TLRequestHelpGetConfig());
                    BotLogger.info(LOGTAG,"Loaded DC list");
                    this.config.getApiState().updateSettings(tlConfig);
                    BotLogger.info(LOGTAG,"Sending code to phone " + this.config.getPhoneNumber() + "...");
                    final TLRequestAuthSendCode tlRequestAuthSendCode = getSendCodeRequest();
                    sentCode = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSendCode);
                    this.createNextCodeTimer(sentCode.getTimeout());
                } catch (RpcException e) {
                    if (e.getErrorCode() == ERROR_303) {
                        final int destDC = updateDCWhenLogin(e);
                        if (destDC != -1) {
                            this.config.getApiState().setPrimaryDc(destDC);
                            this.config.getApi().switchToDc(destDC);
                            sentCode = this.retryLogin(destDC);
                        }
                    }
                } catch (TimeoutException e) {
                    BotLogger.error(LOGTAG, e);
                    sentCode = null;
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, e);
                }

                if (sentCode != null) {
                    this.config.setPhoneCodeHash(sentCode.getPhoneCodeHash());
                    BotLogger.info(LOGTAG,"sent Code");
                    result = LoginStatus.CODESENT;
                } else {
                    result = LoginStatus.ERRORSENDINGCODE;
                }
            }
        } catch (IOException | TimeoutException ex) {
            BotLogger.error(LOGTAG, ex);
            result = LoginStatus.UNEXPECTEDERROR;
        }

        return result;
    }

    private LoginStatus loginBot() {
        LoginStatus result = null;
        try {
            if (this.config.getApiState().isAuthenticated()) {
                BotLogger.info(LOGTAG,"Found Auth file");
                this.config.setRegistered(true);
                result = LoginStatus.ALREADYLOGGED;
            } else {
                try {
                    final TLConfig config = this.config.getApi().doRpcCallNonAuth(new TLRequestHelpGetConfig());
                    BotLogger.info(LOGTAG,"Loaded DC list");
                    this.config.getApiState().updateSettings(config);
                } catch (IOException | TimeoutException e) {
                    BotLogger.error(LOGTAG, e);
                }
                BotLogger.info(LOGTAG,"Sending code to phone " + config.getPhoneNumber() + "...");
                try {
                    final TLRequestAuthImportBotAuthorization botAuthorization = new TLRequestAuthImportBotAuthorization();
                    botAuthorization.setApiId(this.config.getApiId());
                    botAuthorization.setApiHash(this.config.getApiHash());
                    botAuthorization.setBotAuthToken(this.config.getBotToken());
                    TLAbsAuthAuthorization authorization = this.config.getApi().doRpcCallNonAuth(botAuthorization);

                    if (authorization != null) {
                        this.config.setRegistered(true);
                        this.config.getApiState().doAuth(authorization);
                        BotLogger.info(LOGTAG,"Activation complete as #" + this.config.getApiState().getObject().getUid());
                        this.config.getApi().doRpcCall(new TLRequestUpdatesGetState());
                        BotLogger.info(LOGTAG,"Loaded initial state");
                        result =  LoginStatus.BOTLOGIN;
                    }
                } catch (RpcException e) {
                    BotLogger.severe(LOGTAG, e);
                } catch (TimeoutException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        } catch (IOException ex) {
            BotLogger.error(LOGTAG, ex);
            result = LoginStatus.UNEXPECTEDERROR;
        }

        if (result == null) {
            result = LoginStatus.BOTLOGINERROR;
        }

        return result;
    }

    private void createNextCodeTimer(int timeout) {
        this.loginTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    final TLRequestAuthSendCode tlRequestAuthSendCode = KernelAuthenticator.this.getSendCodeRequest();
                    TLSentCode sentCode = KernelAuthenticator.this.config.getApi().doRpcCallNonAuth(tlRequestAuthSendCode);
                    this.cancel();
                    KernelAuthenticator.this.createNextCodeTimer(sentCode.getTimeout());
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }, (timeout == 0) ? TIME_UNTIL_CALLING_USER : timeout);
    }

    private TLRequestAuthSendCode getSendCodeRequest() {
        final TLRequestAuthSendCode tlRequestAuthSendCode = new TLRequestAuthSendCode();
        tlRequestAuthSendCode.setPhoneNumber(this.config.getPhoneNumber());
        tlRequestAuthSendCode.setApiId(this.config.getApiId());
        tlRequestAuthSendCode.setApiHash(this.config.getApiHash());
        tlRequestAuthSendCode.setSettings(new TLCodeSettings());
        return tlRequestAuthSendCode;
    }

    private int updateDCWhenLogin(RpcException e) {
        final int destDC;
        if (e.getErrorTag().startsWith("NETWORK_MIGRATE_")) {
            destDC = Integer.parseInt(e.getErrorTag().substring("NETWORK_MIGRATE_".length()));
        } else if (e.getErrorTag().startsWith("PHONE_MIGRATE_")) {
            destDC = Integer.parseInt(e.getErrorTag().substring("PHONE_MIGRATE_".length()));
        } else if (e.getErrorTag().startsWith("USER_MIGRATE_")) {
            destDC = Integer.parseInt(e.getErrorTag().substring("USER_MIGRATE_".length()));
        } else {
            BotLogger.error(LOGTAG, e);
            destDC = -1;
        }
        return destDC;
    }

    private TLSentCode retryLogin(int destDC) throws IOException, TimeoutException {
        final TLSentCode sentCode;
        this.config.getApi().switchToDc(destDC);
        final TLRequestAuthSendCode tlRequestAuthSendCode = this.getSendCodeRequest();
        sentCode = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSendCode);
        this.resetTimer();
        this.createNextCodeTimer(sentCode.getTimeout());
        return sentCode;
    }

    private void resetTimer() {
        this.loginTimer.cancel();
        this.loginTimer = new Timer();
    }

    private TLRequestAuthSignUp getSignUpRequest() {
        final TLRequestAuthSignUp tlRequestAuthSignUp = new TLRequestAuthSignUp();
        tlRequestAuthSignUp.setPhoneNumber(this.config.getPhoneNumber());
        tlRequestAuthSignUp.setPhoneCodeHash(this.config.getPhoneCodeHash());
        tlRequestAuthSignUp.setFirstName(this.config.getFirstName());
        tlRequestAuthSignUp.setLastName(this.config.getLastName());
        return tlRequestAuthSignUp;
    }

    private TLRequestAuthSignIn getSignInRequest(String code) {
        final TLRequestAuthSignIn tlRequestAuthSignIn = new TLRequestAuthSignIn();
        tlRequestAuthSignIn.setPhoneNumber(this.config.getPhoneNumber());
        tlRequestAuthSignIn.setPhoneCodeHash(this.config.getPhoneCodeHash());
        tlRequestAuthSignIn.setPhoneCode(code);
        return tlRequestAuthSignIn;
    }

}