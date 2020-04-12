package org.telegram.client.handlers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.utils.BotLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.telegram.api.channel.base.filters.TLChannelMessagesFilterEmpty;
import org.telegram.api.channel.base.input.TLInputChannel;
import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.api.update.base.TLAbsUpdate;
import org.telegram.api.updates.base.TLUpdatesState;
import org.telegram.api.updates.base.channel.differences.TLAbsUpdatesChannelDifference;
import org.telegram.api.updates.base.channel.differences.TLUpdatesChannelDifference;
import org.telegram.api.updates.base.channel.differences.TLUpdatesChannelDifferenceEmpty;
import org.telegram.api.updates.base.channel.differences.TLUpdatesChannelDifferenceTooLong;
import org.telegram.api.updates.base.difference.TLAbsDifference;
import org.telegram.api.updates.base.difference.TLDifference;
import org.telegram.api.updates.base.difference.TLDifferenceSlice;
import org.telegram.api.updates.base.difference.TLDifferenceTooLong;
import org.telegram.api.updates.functions.TLRequestUpdatesGetChannelDifference;
import org.telegram.api.updates.functions.TLRequestUpdatesGetDifference;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.MyTLAppConfiguration;
import org.telegram.utils.RpcException;

public class DefaultDifferencesHandler {
    
    private static final String LOGTAG = "DIFFERENCESHANDLER";

    private final Object differencesLock = new Object();
    private MyTLAppConfiguration config;

    public DefaultDifferencesHandler() {
    }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
    }

    public void getDifferences() {
        final boolean canGetDifferences;
        synchronized(this.differencesLock) {
            canGetDifferences = canGetDifferences();
            if (canGetDifferences) {
                BotLogger.info(LOGTAG, "Getting differences");

                final TLRequestUpdatesGetDifference requestUpdatesGetDifference = new TLRequestUpdatesGetDifference();
                requestUpdatesGetDifference.setQts(0);
                TLAbsDifference absDifference = null;
                do {
                    requestUpdatesGetDifference.setDate(this.config.getDifferenceParametersService().getDate(0));
                    requestUpdatesGetDifference.setPts(this.config.getDifferenceParametersService().getPts(0));
                    try {
                        absDifference = this.config.getKernelCommunicationService().doRpcCallSync(requestUpdatesGetDifference);
                        if (absDifference != null) {
                            onTLAbsDifferences(absDifference);
                        }
                    } catch (ExecutionException | RpcException
                            e) {
                        BotLogger.error(LOGTAG, e);
                    }
                    try {
                        synchronized(this) {
                            if ((absDifference instanceof TLDifferenceSlice)) {
                                this.wait(100);
                            }
                        }
                    } catch (InterruptedException e) {
                        BotLogger.error(LOGTAG, e);
                    }
                } while ((absDifference instanceof TLDifferenceSlice));
                BotLogger.info(LOGTAG, "Getting differences finished");
            }
        }
    }

    public void updateStateModification(@NotNull TLUpdatesState state, boolean isGettingDifferent) {
        if (!isGettingDifferent && (this.config.getDifferenceParametersService().getPts(0) != 0) && (this.config.getDifferenceParametersService().getSeq(0) != 0)) {
            this.getDifferences();
        } else {
            this.config.getDifferenceParametersService().setNewUpdateParams(0, state.getPts(), state.getSeq(), state.getDate());
        }
    }

    public void getChannelDifferences(int chatId, long accessHash) {
        if (accessHash == 0) {
            this.getDifferences();
        } else {
            this.getChannelDifferencesInternal(chatId, accessHash);
        }
    }

    private void getChannelDifferencesInternal(int chatId, long accessHash) {
        synchronized(this.differencesLock) {
            BotLogger.info(LOGTAG, "Getting differences");

            final TLRequestUpdatesGetChannelDifference requestGetChannelDifference = new TLRequestUpdatesGetChannelDifference();
            requestGetChannelDifference.setFilter(new TLChannelMessagesFilterEmpty());
            final TLInputChannel inputChannel = new TLInputChannel();
            inputChannel.setChannelId(chatId);
            inputChannel.setAccessHash(accessHash);
            requestGetChannelDifference.setChannel(inputChannel);
            TLAbsUpdatesChannelDifference absDifference = null;
            do {
                final int pts = this.config.getDifferenceParametersService().getPts(chatId);
                requestGetChannelDifference.setPts((pts == 0) ? 1 : pts);
                requestGetChannelDifference.setLimit(100);
                try {
                    absDifference = this.config.getKernelCommunicationService().doRpcCallSync(requestGetChannelDifference);
                    if ((absDifference != null) && !(absDifference instanceof TLUpdatesChannelDifferenceEmpty)) {
                        onTLAbsUpdatesChannelDifference(chatId, absDifference);
                    }
                } catch (ExecutionException | RpcException e) {
                    BotLogger.error(LOGTAG, e);
                }
                try {
                    synchronized (this) {
                        if ((absDifference instanceof TLUpdatesChannelDifferenceTooLong)) {
                            this.wait(100);
                        }
                    }
                } catch (InterruptedException e) {
                    BotLogger.error(LOGTAG, e);
                }
            } while ((absDifference instanceof TLUpdatesChannelDifferenceTooLong));
            BotLogger.info(LOGTAG, "Getting differences finished");
        }
    }

    public void updateChannelStateModification(int chatId, @Nullable Long accessHash, int pts, boolean isGettingDifferent) {
        if (!isGettingDifferent && (this.config.getDifferenceParametersService().getPts(chatId) != 0) && (this.config.getDifferenceParametersService().getSeq(chatId) != 0) && (accessHash != null)) {
            this.getChannelDifferences(chatId, accessHash);
        } else {
            this.config.getDifferenceParametersService().setNewUpdateParams(chatId, pts, null, 0);
        }
    }

    /**
     * Handles TLAbsDifferences
     * @param absDifference AbsDifferences to handle
     */
    private void onTLAbsDifferences(@NotNull TLAbsDifference absDifference) {
        BotLogger.info(LOGTAG, "Received differences");
        final TLUpdatesState updatesState;

        if (absDifference instanceof TLDifferenceSlice) {
            updatesState = ((TLDifferenceSlice) absDifference).getIntermediateState();
            this.handleDifferences(absDifference, updatesState);
        } else if (absDifference instanceof TLDifference) {
            updatesState = ((TLDifference) absDifference).getState();
            this.handleDifferences(absDifference, updatesState);
        } else if (absDifference instanceof TLDifferenceTooLong) {
            TLUpdatesState state = new TLUpdatesState();
            state.setPts(((TLDifferenceTooLong) absDifference).getPts());
            this.updateStateModification(state, true);
        }
    }

    private void onTLAbsUpdatesChannelDifference(int chatId, TLAbsUpdatesChannelDifference absDifference) {
        if (absDifference instanceof TLUpdatesChannelDifference) {
            final TLUpdatesChannelDifference differences = (TLUpdatesChannelDifference) absDifference;
            this.handleChannelDifferences(chatId, differences.getPts(), differences.getUsers(), differences.getNewMessages(), differences.getOtherUpdates(), differences.getChats());
        } else if (absDifference instanceof TLUpdatesChannelDifferenceTooLong) {
            final TLUpdatesChannelDifferenceTooLong differences = (TLUpdatesChannelDifferenceTooLong) absDifference;
            this.handleChannelDifferences(chatId, differences.getPts(), differences.getUsers(), differences.getMessages(), new ArrayList<>(), differences.getChats());
        }
    }

    private void handleChannelDifferences(int chatId, int pts, List<TLAbsUser> users, List<TLAbsMessage> messages, List<TLAbsUpdate> updates, List<TLAbsChat> chats) {
        BotLogger.info(LOGTAG, "Handling channel differences");
        this.config.getUpdatesHandler().onTLChannelDifferences(users, messages, updates, chats);
        this.updateChannelStateModification(chatId, null, pts, true);
    }

    /**
     * Handle Differences
     * @param absDifference AbsDifferences to handle
     * @param updatesState Updates state of differences
     */
    private void handleDifferences(@NotNull TLAbsDifference absDifference, @NotNull TLUpdatesState updatesState) {
        BotLogger.info(LOGTAG, "Handling differences");
        this.config.getUpdatesHandler().onTLAbsDifference(absDifference);
        this.updateStateModification(updatesState, true);
    }

    @Contract(pure = true)
    private boolean canGetDifferences() {
        return (this.config.getDifferenceParametersService().getPts(0) != 0) && (this.config.getDifferenceParametersService().getSeq(0) != 0);
    }

}