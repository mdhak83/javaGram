package org.javagram.client.kernel;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.javagram.api.TLConfig;
import org.javagram.api.auth.base.authorization.TLAbsAuthAuthorization;
import org.javagram.api.auth.base.sentcode.TLSentCode;
import org.javagram.api.auth.functions.TLRequestAuthImportBotAuthorization;
import org.javagram.api.auth.functions.TLRequestAuthLogOut;
import org.javagram.api.auth.functions.TLRequestAuthSendCode;
import org.javagram.api.auth.functions.TLRequestAuthSignIn;
import org.javagram.api.auth.functions.TLRequestAuthSignUp;
import org.javagram.api.help.functions.TLRequestHelpGetConfig;
import org.javagram.api.updates.functions.TLRequestUpdatesGetState;
import org.javagram.MyTLAppConfiguration;
import org.javagram.api.TLCodeSettings;
import org.javagram.utils.RpcException;
import org.javagram.utils.BotLogger;
import org.javagram.client.structure.LoginStatus;

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
        if (this.config.getApiState().isAuthenticated()) {
            // Logout previous
            boolean ok = false;
            while (!ok) {
                try {
                    this.config.getKernelCommunicationService().doRpcCallSync(new TLRequestAuthLogOut());
                    ok = true;
                } catch (Exception ex) {
                    BotLogger.error(LOGTAG, "[logOut] TLRequestAuthLogOut error. Retrying in 1s.");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex2) {}
                    ok = false;
                }
            }
            // Reset previous stored credentials
            this.config.getApiState().resetAuth();
        }
        this.config.setRegistered(false);
        return true;
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
                TLAbsAuthAuthorization authorization = null;
                if (this.config.isRegistered()) {
                    final TLRequestAuthSignIn tlRequestAuthSignIn = getSignInRequest(code);
                    boolean ok = false;
                    while (!ok) {
                        try {
                            authorization = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSignIn);
                            ok = true;
                        } catch (TimeoutException ex) {
                            BotLogger.error(LOGTAG, "[setAuthCode] TLRequestAuthSignIn timeout. Retrying in 1s.");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex2) {}
                            ok = false;
                        }
                    }
                } else {
                    final TLRequestAuthSignUp tlRequestAuthSignUp = getSignUpRequest();
                    boolean ok = false;
                    while (!ok) {
                        try {
                            authorization = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSignUp);
                            ok = true;
                        } catch (TimeoutException ex) {
                            BotLogger.error(LOGTAG, "[login] TLRequestAuthSignUp timeout. Retrying in 1s.");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex2) {}
                            ok = false;
                        }
                    }
                    
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
                    TLConfig tlConfig = null;
                    boolean ok = false;
                    while (!ok) {
                        try {
                            tlConfig = this.config.getApi().doRpcCallNonAuth(new TLRequestHelpGetConfig());
                            ok = true;
                        } catch (TimeoutException ex) {
                            BotLogger.error(LOGTAG, "[login] TLRequestHelpGetConfig timeout. Retrying in 1s.");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex2) {}
                            ok = false;
                        }
                    }
                    BotLogger.info(LOGTAG,"Loaded DC list");
                    this.config.getApiState().updateSettings(tlConfig);
                    BotLogger.info(LOGTAG,"Sending code to phone " + this.config.getPhoneNumber() + "...");
                    final TLRequestAuthSendCode tlRequestAuthSendCode = getSendCodeRequest();
                    ok = false;
                    while (!ok) {
                        try {
                            sentCode = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSendCode);
                            ok = true;
                        } catch (TimeoutException ex) {
                            BotLogger.error(LOGTAG, "[login] TLRequestAuthSendCode timeout. Retrying in 1s.");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex2) {}
                            ok = false;
                        }
                    }
                    BotLogger.info(LOGTAG, "TLRequestAuthSendCode OK.");
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
                    TLConfig config = null;
                    boolean ok = false;
                    while (!ok) {
                        try {
                            config = this.config.getApi().doRpcCallNonAuth(new TLRequestHelpGetConfig());
                            ok = true;
                        } catch (TimeoutException ex) {
                            BotLogger.error(LOGTAG, "[loginBot] TLRequestHelpGetConfig timeout. Retrying in 1s.");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex2) {}
                            ok = false;
                        }
                    }
                    BotLogger.info(LOGTAG,"Loaded DC list");
                    this.config.getApiState().updateSettings(config);
                } catch (IOException e) {
                    BotLogger.error(LOGTAG, e);
                }
                BotLogger.info(LOGTAG,"Sending code to phone " + config.getPhoneNumber() + "...");
                try {
                    final TLRequestAuthImportBotAuthorization botAuthorization = new TLRequestAuthImportBotAuthorization();
                    botAuthorization.setApiId(this.config.getApiId());
                    botAuthorization.setApiHash(this.config.getApiHash());
                    botAuthorization.setBotAuthToken(this.config.getBotToken());
                    TLAbsAuthAuthorization authorization = null;
                    boolean ok = false;
                    while (!ok) {
                        try {
                            authorization = this.config.getApi().doRpcCallNonAuth(botAuthorization);
                            ok = true;
                        } catch (TimeoutException ex) {
                            BotLogger.error(LOGTAG, "[loginBot] TLRequestAuthImportBotAuthorization timeout. Retrying in 1s.");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex2) {}
                            ok = false;
                        }
                    }

                    if (authorization != null) {
                        this.config.setRegistered(true);
                        this.config.getApiState().doAuth(authorization);
                        BotLogger.info(LOGTAG,"Activation complete as #" + this.config.getApiState().getObject().getUid());
                        ok = false;
                        while (!ok) {
                            try {
                                this.config.getApi().doRpcCall(new TLRequestUpdatesGetState());
                                ok = true;
                            } catch (TimeoutException ex) {
                                BotLogger.error(LOGTAG, "[loginBot] TLRequestUpdatesGetState timeout. Retrying in 1s.");
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception ex2) {}
                                ok = false;
                            }
                        }
                        BotLogger.info(LOGTAG,"Loaded initial state");
                        result =  LoginStatus.BOTLOGIN;
                    }
                } catch (RpcException e) {
                    BotLogger.severe(LOGTAG, e);
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
                    TLSentCode sentCode = null;
                    boolean ok = false;
                    while (!ok) {
                        try {
                            sentCode = KernelAuthenticator.this.config.getApi().doRpcCallNonAuth(tlRequestAuthSendCode);
                            ok = true;
                        } catch (TimeoutException ex) {
                            BotLogger.error(LOGTAG, "[createNextCodeTimer] TLRequestAuthSendCode timeout. Retrying in 1s.");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex2) {}
                            ok = false;
                        }
                    }
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
        this.config.getApi().switchToDc(destDC);
        final TLRequestAuthSendCode tlRequestAuthSendCode = this.getSendCodeRequest();
        TLSentCode sentCode = null;
        boolean ok = false;
        while (!ok) {
            try {
                sentCode = this.config.getApi().doRpcCallNonAuth(tlRequestAuthSendCode);
                ok = true;
            } catch (TimeoutException ex) {
                BotLogger.error(LOGTAG, "[retryLogin] TLRequestAuthSendCode timeout. Retrying in 1s.");
                try {
                    Thread.sleep(1000);
                } catch (Exception ex2) {}
                ok = false;
            }
        }
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