package org.javagram.client.handlers;

import org.javagram.client.handlers.interfaces.IUpdatesHandler;
import org.javagram.api.update.base.TLUpdateBotWebhookJSONQuery;
import org.javagram.api.update.base.TLUpdateReadChannelOutbox;
import org.javagram.api.update.base.TLUpdateInlineBotCallbackQuery;
import org.javagram.api.update.base.TLUpdateChannelWebPage;
import org.javagram.api.update.base.TLUpdateNewMessage;
import org.javagram.api.update.base.TLUpdateUserBlocked;
import org.javagram.api.update.base.TLUpdateMessageId;
import org.javagram.api.update.base.TLUpdateEditChannelMessage;
import org.javagram.api.update.base.TLUpdateDeleteMessages;
import org.javagram.api.update.base.TLUpdateEditMessage;
import org.javagram.api.update.base.TLUpdateBotPrecheckoutQuery;
import org.javagram.api.update.base.TLUpdateUserTyping;
import org.javagram.api.update.base.TLUpdateDialogPinned;
import org.javagram.api.update.base.TLUpdateUserStatus;
import org.javagram.api.update.base.TLUpdateUserPhone;
import org.javagram.api.update.base.TLUpdateChatParticipants;
import org.javagram.api.update.base.TLUpdateDeleteChannelMessages;
import org.javagram.api.update.base.TLUpdateReadMessagesInbox;
import org.javagram.api.update.base.TLUpdateChatParticipantAdd;
import org.javagram.api.update.base.TLUpdateWebPage;
import org.javagram.api.update.base.TLUpdateStickerSets;
import org.javagram.api.update.base.TLUpdateBotInlineSend;
import org.javagram.api.update.base.TLUpdateNewStickerSet;
import org.javagram.api.update.base.TLUpdateDcOptions;
import org.javagram.api.update.base.TLUpdateBotShippingQuery;
import org.javagram.api.update.base.TLUpdatePhoneCall;
import org.javagram.api.update.base.TLUpdateBotCallbackQuery;
import org.javagram.api.update.base.TLUpdateUserName;
import org.javagram.api.update.base.TLUpdateServiceNotification;
import org.javagram.api.update.base.TLUpdateChannelNewMessage;
import org.javagram.api.update.base.TLUpdatePtsChanged;
import org.javagram.api.update.base.TLUpdatePinnedDialogs;
import org.javagram.api.update.base.TLUpdateConfig;
import org.javagram.api.update.base.TLUpdateNotifySettings;
import org.javagram.api.update.base.TLUpdateDraftMessage;
import org.javagram.api.update.base.TLUpdateRecentStickers;
import org.javagram.api.update.base.TLFakeUpdate;
import org.javagram.api.update.base.TLUpdateReadMessagesOutbox;
import org.javagram.api.update.base.TLUpdateReadFeaturedStickers;
import org.javagram.api.update.base.TLUpdateChatParticipantDelete;
import org.javagram.api.update.base.TLUpdateChannelTooLong;
import org.javagram.api.update.base.TLUpdateSavedGifs;
import org.javagram.api.update.base.TLUpdateReadChannelInbox;
import org.javagram.api.update.base.TLUpdateChannelMessageViews;
import org.javagram.api.update.base.TLUpdateUserPhoto;
import org.javagram.api.update.base.TLAbsUpdate;
import org.javagram.api.update.base.TLUpdateBotWebhookJSON;
import org.javagram.api.update.base.TLUpdatePrivacy;
import org.javagram.api.update.base.TLUpdateChannelPinnedMessage;
import org.javagram.api.update.base.TLUpdateStickerSetsOrder;
import org.javagram.api.update.base.TLUpdateReadMessagesContents;
import org.javagram.api.update.base.TLUpdateBotInlineQuery;
import org.javagram.api.update.base.TLUpdateChatUserTyping;
import org.javagram.api.update.base.TLUpdateChatParticipantAdmin;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.update.base.encrypted.TLUpdateEncryptedChatTyping;
import org.javagram.api.update.base.encrypted.TLUpdateEncryptedMessagesRead;
import org.javagram.api.update.base.encrypted.TLUpdateEncryption;
import org.javagram.api.update.base.encrypted.TLUpdateNewEncryptedMessage;
import org.javagram.api.updates.base.TLUpdateShortChatMessage;
import org.javagram.api.updates.base.TLUpdateShortMessage;
import org.javagram.api.updates.base.TLUpdateShortSentMessage;
import org.javagram.api.updates.base.difference.TLAbsDifference;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.client.structure.UpdateWrapper;
import org.javagram.utils.BotLogger;
import org.javagram.api._primitives.TLObject;
import java.util.List;
import org.javagram.api.update.base.TLUpdateChannelAvailableMessages;
import org.javagram.api.update.base.TLUpdateChannelReadMessagesContents;
import org.javagram.api.update.base.TLUpdateChatDefaultBannedRights;
import org.javagram.api.update.base.TLUpdateChatPinnedMessage;
import org.javagram.api.update.base.TLUpdateContactsReset;
import org.javagram.api.update.base.TLUpdateDeleteScheduledMessage;
import org.javagram.api.update.base.TLUpdateDialogUnreadMark;
import org.javagram.api.update.base.TLUpdateFavedSticker;
import org.javagram.api.update.base.TLUpdateFolderPeers;
import org.javagram.api.update.base.TLUpdateGeoLiveViewed;
import org.javagram.api.update.base.TLUpdateLangPack;
import org.javagram.api.update.base.TLUpdateLangPackTooLong;
import org.javagram.api.update.base.TLUpdateLoginToken;
import org.javagram.api.update.base.TLUpdateMessagePoll;
import org.javagram.api.update.base.TLUpdateNewScheduledMessage;
import org.javagram.api.update.base.TLUpdatePeerLocated;
import org.javagram.api.update.base.TLUpdatePeerSettings;
import org.javagram.api.update.base.TLUpdateReadHistoryInbox;
import org.javagram.api.update.base.TLUpdateTheme;
import org.javagram.api.update.base.TLUpdateUserPinnedMessage;
import org.javagram.api.updates.base.TLUpdateShort;
import org.javagram.MyTLAppConfiguration;
import org.javagram.api.update.base.TLUpdateChannel;
import org.javagram.api.updates.base.TLUpdatesState;

/**
 * Abstract class of the updates handler, should be provided to MainHandler.
 * Has some final method, but implementation must provide custom behaviour (even empty)
 */
public abstract class AbstractUpdatesHandler implements IUpdatesHandler {
    
    private static final String LOGTAG = "[AbstractUpdatesHandler]";

    protected MyTLAppConfiguration config;

    protected AbstractUpdatesHandler() {
    }
    
    public void build(MyTLAppConfiguration config) {
        this.config = config;
        if (this.config.getDifferencesHandler() == null) {
            this.config.setDifferencesHandler(new DefaultDifferencesHandler());
        }
        this.config.getDifferencesHandler().build(this.config);
    }

    @Override
    public final void processUpdate(UpdateWrapper updateWrapper) {
        boolean canHandle = true;
        if (updateWrapper.isCheckPts()) {
            canHandle = checkPts(updateWrapper);
        }

        if (canHandle) {
            final TLObject update = updateWrapper.getUpdate();
            BotLogger.debug(LOGTAG, "Received update: " + update.toString());
            /** *****************************
             *  *****************************
             *       Update
             *  *****************************
             ** *****************************/
            if (update instanceof TLFakeUpdate) {
                this.onTLFakeUpdate((TLFakeUpdate) update);
            } else if (update instanceof TLUpdateBotCallbackQuery) {
                this.onTLUpdateBotCallbackQuery((TLUpdateBotCallbackQuery) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateBotInlineQuery) {
                this.onTLUpdateBotInlineQuery((TLUpdateBotInlineQuery) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateBotInlineSend) {
                this.onTLUpdateBotInlineSend((TLUpdateBotInlineSend) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateBotPrecheckoutQuery) {
                this.onTLUpdateBotPrecheckoutQuery((TLUpdateBotPrecheckoutQuery) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateBotShippingQuery) {
                this.onTLUpdateBotShippingQuery((TLUpdateBotShippingQuery) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateBotWebhookJSON) {
                this.onTLUpdateBotWebhookJSON((TLUpdateBotWebhookJSON) update);
            } else if (update instanceof TLUpdateBotWebhookJSONQuery) {
                this.onTLUpdateBotWebhookJSONQuery((TLUpdateBotWebhookJSONQuery) update);
            } else if (update instanceof TLUpdateChannelTooLong) {
                this.onTLUpdateChannelTooLong((TLUpdateChannelTooLong) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChannel) {
                this.onTLUpdateChannel((TLUpdateChannel) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChannelAvailableMessages) {
                this.onTLUpdateChannelAvailableMessages((TLUpdateChannelAvailableMessages) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChannelMessageViews) {
                this.onTLUpdateChannelMessageViews((TLUpdateChannelMessageViews) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChannelNewMessage) {
                this.onTLUpdateChannelNewMessage((TLUpdateChannelNewMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChannelPinnedMessage) {
                this.onTLUpdateChannelPinnedMessage((TLUpdateChannelPinnedMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChannelReadMessagesContents) {
                this.onTLUpdateChannelReadMessagesContents((TLUpdateChannelReadMessagesContents) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChannelWebPage) {
                this.onTLUpdateChannelWebPage((TLUpdateChannelWebPage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChatDefaultBannedRights) {
                this.onTLUpdateChatDefaultBannedRights((TLUpdateChatDefaultBannedRights) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChatParticipantAdd) {
                this.onTLUpdateChatParticipantAdd((TLUpdateChatParticipantAdd) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChatParticipantAdmin) {
                this.onTLUpdateChatParticipantAdmin((TLUpdateChatParticipantAdmin) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChatParticipantDelete) {
                this.onTLUpdateChatParticipantDelete((TLUpdateChatParticipantDelete) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChatParticipants) {
                this.onTLUpdateChatParticipants((TLUpdateChatParticipants) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChatPinnedMessage) {
                this.onTLUpdateChatPinnedMessage((TLUpdateChatPinnedMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateChatUserTyping) {
                this.onTLUpdateChatUserTyping((TLUpdateChatUserTyping) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateConfig) {
                this.onTLUpdateConfig((TLUpdateConfig) update);
            } else if (update instanceof TLUpdateContactsReset) {
                this.onTLUpdateContactsReset((TLUpdateContactsReset) update);
            } else if (update instanceof TLUpdateDcOptions) {
                this.onTLUpdateDcOptions((TLUpdateDcOptions) update);
            } else if (update instanceof TLUpdateDeleteChannelMessages) {
                this.onTLUpdateDeleteChannelMessages((TLUpdateDeleteChannelMessages) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateDeleteMessages) {
                this.onTLUpdateDeleteMessages((TLUpdateDeleteMessages) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateDeleteScheduledMessage) {
                this.onTLUpdateDeleteScheduledMessage((TLUpdateDeleteScheduledMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateDialogPinned) {
                this.onTLUpdateDialogPinned((TLUpdateDialogPinned) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateDialogUnreadMark) {
                this.onTLUpdateDialogUnreadMark((TLUpdateDialogUnreadMark) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateDraftMessage) {
                this.onTLUpdateDraftMessage((TLUpdateDraftMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateEditChannelMessage) {
                this.onTLUpdateEditChannelMessage((TLUpdateEditChannelMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateEditMessage) {
                this.onTLUpdateEditMessage((TLUpdateEditMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateFavedSticker) {
                this.onTLUpdateFavedSticker((TLUpdateFavedSticker) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateFolderPeers) {
                this.onTLUpdateFolderPeers((TLUpdateFolderPeers) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateGeoLiveViewed) {
                this.onTLUpdateGeoLiveViewed((TLUpdateGeoLiveViewed) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateInlineBotCallbackQuery) {
                this.onTLUpdateInlineBotCallbackQuery((TLUpdateInlineBotCallbackQuery) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateLangPack) {
                this.onTLUpdateLangPack((TLUpdateLangPack) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateLangPackTooLong) {
                this.onTLUpdateLangPackTooLong((TLUpdateLangPackTooLong) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateLoginToken) {
                this.onTLUpdateLoginToken((TLUpdateLoginToken) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateMessageId) {
                this.onTLUpdateMessageId((TLUpdateMessageId) update);
            } else if (update instanceof TLUpdateMessagePoll) {
                this.onTLUpdateMessagePoll((TLUpdateMessagePoll) update);
            } else if (update instanceof TLUpdateNewMessage) {
                this.onTLUpdateNewMessage((TLUpdateNewMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateNewScheduledMessage) {
                this.onTLUpdateNewScheduledMessage((TLUpdateNewScheduledMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateNewStickerSet) {
                this.onTLUpdateNewStickerSet((TLUpdateNewStickerSet) update);
            } else if (update instanceof TLUpdateNotifySettings) {
                this.onTLUpdateNotifySettings((TLUpdateNotifySettings) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdatePeerLocated) {
                this.onTLUpdatePeerLocated((TLUpdatePeerLocated) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdatePeerSettings) {
                this.onTLUpdatePeerSettings((TLUpdatePeerSettings) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdatePhoneCall) {
                this.onTLUpdatePhoneCall((TLUpdatePhoneCall) update);
            } else if (update instanceof TLUpdatePinnedDialogs) {
                this.onTLUpdatePinnedDialogs((TLUpdatePinnedDialogs) update);
            } else if (update instanceof TLUpdatePrivacy) {
                this.onTLUpdatePrivacy((TLUpdatePrivacy) update);
            } else if (update instanceof TLUpdatePtsChanged) {
                this.onTLUpdatePtsChanged((TLUpdatePtsChanged) update);
            } else if (update instanceof TLUpdateReadChannelInbox) {
                this.onTLUpdateReadChannelInbox((TLUpdateReadChannelInbox) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateReadChannelOutbox) {
                this.onTLUpdateReadChannelOutbox((TLUpdateReadChannelOutbox) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateReadFeaturedStickers) {
                this.onTLUpdateReadFeaturedStickers((TLUpdateReadFeaturedStickers) update);
            } else if (update instanceof TLUpdateReadHistoryInbox) {
                this.onTLUpdateReadHistoryInbox((TLUpdateReadHistoryInbox) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateReadMessagesContents) {
                this.onTLUpdateReadMessagesContents((TLUpdateReadMessagesContents) update);
            } else if (update instanceof TLUpdateReadMessagesInbox) {
                this.onTLUpdateReadMessagesInbox((TLUpdateReadMessagesInbox) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateReadMessagesOutbox) {
                this.onTLUpdateReadMessagesOutbox((TLUpdateReadMessagesOutbox) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateRecentStickers) {
                this.onTLUpdateRecentStickers((TLUpdateRecentStickers) update);
            } else if (update instanceof TLUpdateSavedGifs) {
                this.onTLUpdateSavedGifs((TLUpdateSavedGifs) update);
            } else if (update instanceof TLUpdateServiceNotification) {
                this.onTLUpdateServiceNotification((TLUpdateServiceNotification) update);
            } else if (update instanceof TLUpdateStickerSets) {
                this.onTLUpdateStickerSets((TLUpdateStickerSets) update);
            } else if (update instanceof TLUpdateStickerSetsOrder) {
                this.onTLUpdateStickerSetsOrder((TLUpdateStickerSetsOrder) update);
            } else if (update instanceof TLUpdateTheme) {
                this.onTLUpdateTheme((TLUpdateTheme) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateUserBlocked) {
                this.onTLUpdateUserBlocked((TLUpdateUserBlocked) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateUserName) {
                this.onTLUpdateUserName((TLUpdateUserName) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateUserPhone) {
                this.onTLUpdateUserPhone((TLUpdateUserPhone) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateUserPhoto) {
                this.onTLUpdateUserPhoto((TLUpdateUserPhoto) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateUserPinnedMessage) {
                this.onTLUpdateUserPinnedMessage((TLUpdateUserPinnedMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateUserStatus) {
                this.onTLUpdateUserStatus((TLUpdateUserStatus) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateUserTyping) {
                this.onTLUpdateUserTyping((TLUpdateUserTyping) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateWebPage) {
                this.onTLUpdateWebPage((TLUpdateWebPage) update);
            /** ***************************** 
             *  *****************************
             *       Update.encrypted
             *  *****************************
             ** *****************************/
            } else if (update instanceof TLUpdateEncryptedChatTyping) {
                this.onTLUpdateEncryptedChatTyping((TLUpdateEncryptedChatTyping) update);
            } else if (update instanceof TLUpdateEncryptedMessagesRead) {
                this.onTLUpdateEncryptedMessagesRead((TLUpdateEncryptedMessagesRead) update);
            } else if (update instanceof TLUpdateEncryption) {
                this.onTLUpdateEncryption((TLUpdateEncryption) update);
            } else if (update instanceof TLUpdateNewEncryptedMessage) {
                this.onTLUpdateNewEncryptedMessage((TLUpdateNewEncryptedMessage) update);
            /** *****************************
             *  *****************************
             *       Updates
             *  *****************************
             ** *****************************/
            } else if (update instanceof TLUpdateShort) {
                this.onTLUpdateShort((TLUpdateShort) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateShortChatMessage) {
                this.onTLUpdateShortChatMessage((TLUpdateShortChatMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateShortMessage) {
                this.onTLUpdateShortMessage((TLUpdateShortMessage) update, updateWrapper.isGettingDifferences());
            } else if (update instanceof TLUpdateShortSentMessage) {
                this.onTLUpdateShortSentMessage((TLUpdateShortSentMessage) update);
            /** *****************************
             *  *****************************
             *       Others
             *  *****************************
             ** *****************************/
            } else {
                BotLogger.debug(LOGTAG, "Unsupported TLAbsUpdate: " + update.toString());
            }
            if (updateWrapper.isUpdatePts()){
                this.updatePts(updateWrapper);
            }
        }
    }

    private boolean checkPts(UpdateWrapper updateWrapper) {
        final boolean canHandle;

        final int pts = this.config.getDifferenceParametersService().getPts(updateWrapper.getChannelId());
        final int newPts = pts + updateWrapper.getPtsCount();
        
        if ((updateWrapper.getPts() == 0) || (newPts == updateWrapper.getPts())) {
            canHandle = true;
        } else {
            BotLogger.warning(LOGTAG, "Discarded " + updateWrapper.toString() + " with newPts: " + newPts + "(" + pts +") and pts: " + updateWrapper.getPts());
            canHandle = false;
            if (newPts < updateWrapper.getPts()) {
                if (!updateWrapper.isChannel() || this.config.getDatabaseManager().isChatMissing(updateWrapper.getChannelId())) {
                    this.getDifferences();
                } else {
                    final TLAbsChat chat = this.config.getDatabaseManager().getChatById(updateWrapper.getChannelId(), null);
                    if (chat != null) {
                        this.config.getDifferencesHandler().getChannelDifferences(chat.getId(), chat.getAccessHash());
                    }
                }
            }
        }

        return canHandle;
    }

    @Override
    public final boolean checkSeq(int seq, int seqStart, int date) {
        boolean canHandle = false;
        seqStart = (seqStart == 0) ? seq : seqStart;
        if (seqStart == (this.config.getDifferenceParametersService().getSeq(0) + 1)) {
            canHandle = true;
        }
        return canHandle;
    }

    @Override
    public final void getDifferences() {
        this.config.getDifferencesHandler().getDifferences();
    }

    private void updatePts(UpdateWrapper updateWrapper) {
        this.config.getDifferenceParametersService().setNewUpdateParams(updateWrapper.getChannelId(), updateWrapper.getPts(), updateWrapper.getSeq(), updateWrapper.getDate());
    }

    @Override
    public final void updateStateModification(TLUpdatesState state) {
        this.config.getDifferencesHandler().updateStateModification(state, false);
    }

    /** *****************************
     *  *****************************
     *       Updates
     *  *****************************
     ** *****************************/

    private void onTLUpdateShort(TLUpdateShort update, boolean gettingDifferences) {
        this.onTLUpdateShortCustom(update);
    }

    private void onTLUpdateShortChatMessage(TLUpdateShortChatMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChatId()) || this.config.getDatabaseManager().isUserMissing(update.getFromId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateShortChatMessageCustom(update);
        }
    }

    private void onTLUpdateShortMessage(TLUpdateShortMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserFromShortMessageMissing(update)) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateShortMessageCustom(update);
        }
    }

    private void onTLUpdateShortSentMessage(TLUpdateShortSentMessage update) {
        this.onTLUpdateShortSentMessageCustom(update);
    }

    @Override
    public final void onTLUpdatesTooLong() {
        this.config.getDifferencesHandler().getDifferences();
    }

    // *****************************
    // *****************************
    //   Updates.channel.differences
    // *****************************
    // *****************************

    /**
     * 
     * @param users Differences for USERS
     * @param messages Differences for messages
     * @param newUpdates Differences for newUpdates
     * @param chats Differences for CHATS
     */
    @Override
    public final void onTLChannelDifferences(List<TLAbsUser> users, List<TLAbsMessage> messages, List<TLAbsUpdate> newUpdates, List<TLAbsChat> chats) {
        this.config.getUsersHandler().onUsers(users);
        this.config.getChatsHandler().onChats(chats);
        this.config.getMessagesHandler().onMessages(messages);
        newUpdates.stream().map(update -> {
            UpdateWrapper updateWrapper = new UpdateWrapper(update);
            updateWrapper.disablePtsCheck();
            updateWrapper.disableUpdatePts();
            return updateWrapper;
        }).forEach(this::processUpdate);
    }

    // *****************************
    // *****************************
    //   Updates.difference
    // *****************************
    // *****************************/

    @Override
    public final void onTLAbsDifference(TLAbsDifference absDifference) {
        this.config.getUsersHandler().onUsers(absDifference.getUsers());
        this.config.getChatsHandler().onChats(absDifference.getChats());
        this.config.getMessagesHandler().onMessages(absDifference.getNewMessages());
        absDifference.getOtherUpdates().stream().map(otherUpdate -> {
            UpdateWrapper updateWrapper = new UpdateWrapper(otherUpdate);
            updateWrapper.disablePtsCheck();
            updateWrapper.disableUpdatePts();
            updateWrapper.enableGettingDifferences();
            return updateWrapper;
        }).forEach(this::processUpdate);
    }

    /** *****************************
     *  *****************************
     *       Update
     *  *****************************
     ** *****************************/

    private void onTLFakeUpdate(TLFakeUpdate update) {
        this.onTLFakeUpdateCustom(update);
    }

    private void onTLUpdateBotCallbackQuery(TLUpdateBotCallbackQuery update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId()) || this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateBotCallbackQueryCustom(update);
        }
    }

    private void onTLUpdateBotInlineQuery(TLUpdateBotInlineQuery update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateBotInlineQueryCustom(update);
        }
    }

    private void onTLUpdateBotInlineSend(TLUpdateBotInlineSend update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateBotInlineSendCustom(update);
        }
    }

    private void onTLUpdateBotPrecheckoutQuery(TLUpdateBotPrecheckoutQuery update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateBotPrecheckoutQueryCustom(update);
        }
    }

    private void onTLUpdateBotShippingQuery(TLUpdateBotShippingQuery update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateBotShippingQueryCustom(update);
        }
    }

    private void onTLUpdateBotWebhookJSON(TLUpdateBotWebhookJSON update) {
        this.onTLUpdateBotWebhookJSONCustom(update);
    }

    private void onTLUpdateBotWebhookJSONQuery(TLUpdateBotWebhookJSONQuery update) {
        this.onTLUpdateBotWebhookJSONQueryCustom(update);
    }

    private void onTLUpdateChannelAvailableMessages(TLUpdateChannelAvailableMessages update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChannelAvailableMessagesCustom(update);
        }
    }

    private void onTLUpdateChannelMessageViews(TLUpdateChannelMessageViews update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChannelMessageViewsCustom(update);
        }
    }

    private void onTLUpdateChannelNewMessage(TLUpdateChannelNewMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserFromMessageMissing(update.getMessage(), false)) {
            if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
                if (!gettingDifferences) {
                    this.config.getDifferencesHandler().getDifferences();
                }
            } else {
                final TLAbsChat channel = this.config.getDatabaseManager().getChatById(update.getChannelId(), true);
                if (channel != null) {
                    this.config.getDifferencesHandler().getChannelDifferences(channel.getId(), channel.getAccessHash());
                }
            }
        } else {
            this.onTLUpdateChannelNewMessageCustom(update);
        }
    }

    private void onTLUpdateChannelPinnedMessage(TLUpdateChannelPinnedMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChannelPinnedMessageCustom(update);
        }
    }

    private void onTLUpdateChannelReadMessagesContents(TLUpdateChannelReadMessagesContents update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelID())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChannelReadMessagesContentsCustom(update);
        }
    }

    private void onTLUpdateChannelTooLong(TLUpdateChannelTooLong update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            final TLAbsChat channel = this.config.getDatabaseManager().getChatById(update.getChannelId(), true);
            if (channel != null) {
                this.config.getDifferencesHandler().getChannelDifferences(channel.getId(), channel.getAccessHash());
            }
        }
    }

    private void onTLUpdateChannel(TLUpdateChannel update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChannelCustom(update);
        }
    }

    private void onTLUpdateChannelWebPage(TLUpdateChannelWebPage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChannelWebPageCustom(update);
        }
    }

    private void onTLUpdateChatDefaultBannedRights(TLUpdateChatDefaultBannedRights update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChatDefaultBannedRightsCustom(update);
        }
    }

    private void onTLUpdateChatParticipantAdd(TLUpdateChatParticipantAdd update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChatId()) || this.config.getDatabaseManager().isUserMissing(update.getUserId()) || this.config.getDatabaseManager().isUserMissing(update.getInviterId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChatParticipantAddCustom(update);
        }
    }

    private void onTLUpdateChatParticipantAdmin(TLUpdateChatParticipantAdmin update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChatId()) || this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChatParticipantAdminCustom(update);
        }
    }

    private void onTLUpdateChatParticipantDelete(TLUpdateChatParticipantDelete update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChatId()) || this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChatParticipantDeleteCustom(update);
        }
    }

    private void onTLUpdateChatParticipants(TLUpdateChatParticipants update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getParticipants().getChatId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChatParticipantsCustom(update);
        }
    }

    private void onTLUpdateChatPinnedMessage(TLUpdateChatPinnedMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChatId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChatPinnedMessageCustom(update);
        }
    }

    private void onTLUpdateChatUserTyping(TLUpdateChatUserTyping update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChatId()) || this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateChatUserTypingCustom(update);
        }
    }

    private void onTLUpdateConfig(TLUpdateConfig update) {
        this.onTLUpdateConfigCustom(update);
    }

    private void onTLUpdateContactsReset(TLUpdateContactsReset update) {
        this.onTLUpdateContactsResetCustom(update);
    }

    private void onTLUpdateDcOptions(TLUpdateDcOptions update) {
        this.onTLUpdateDcOptionsCustom(update);
    }

    private void onTLUpdateDeleteChannelMessages(TLUpdateDeleteChannelMessages update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateDeleteChannelMessagesCustom(update);
        }
    }

    private void onTLUpdateDeleteMessages(TLUpdateDeleteMessages update, boolean gettingDifferences) {
        this.onTLUpdateDeleteMessagesCustom(update);
    }

    private void onTLUpdateDeleteScheduledMessage(TLUpdateDeleteScheduledMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateDeleteScheduledMessageCustom(update);
        }
    }

    private void onTLUpdateDialogPinned(TLUpdateDialogPinned update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateDialogPinnedCustom(update);
        }
    }

    private void onTLUpdateDialogUnreadMark(TLUpdateDialogUnreadMark update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateDialogUnreadMarkCustom(update);
        }
    }

    private void onTLUpdateDraftMessage(TLUpdateDraftMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateDraftMessageCustom(update);
        }
    }

    private void onTLUpdateEditChannelMessage(TLUpdateEditChannelMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserFromMessageMissing(update.getMessage(), false)) {
            if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
                if (!gettingDifferences) {
                    this.config.getDifferencesHandler().getDifferences();
                }
            } else {
                final TLAbsChat channel = this.config.getDatabaseManager().getChatById(update.getChannelId(), true);
                if (channel != null) {
                    this.config.getDifferencesHandler().getChannelDifferences(channel.getId(), channel.getAccessHash());
                }
            }
        } else {
            this.onTLUpdateEditChannelMessageCustom(update);
        }
    }

    private void onTLUpdateEditMessage(TLUpdateEditMessage update, boolean gettingDifferences){
        if (this.config.getDatabaseManager().isUserFromMessageMissing(update.getMessage())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateEditMessageCustom(update);
        }
    }

    private void onTLUpdateFavedSticker(TLUpdateFavedSticker update, boolean gettingDifferences) {
        this.onTLUpdateFavedStickerCustom(update);
    }

    private void onTLUpdateFolderPeers(TLUpdateFolderPeers update, boolean gettingDifferences){
        if (this.config.getDatabaseManager().isAnyPeerMissing(update.getFolderPeers())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateFolderPeersCustom(update);
        }
    }

    private void onTLUpdateGeoLiveViewed(TLUpdateGeoLiveViewed update, boolean gettingDifferences){
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateGeoLiveViewedCustom(update);
        }
    }

    private void onTLUpdateInlineBotCallbackQuery(TLUpdateInlineBotCallbackQuery update, boolean gettingDifferences){
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateInlineBotCallbackQueryCustom(update);
        }
    }

    private void onTLUpdateLangPack(TLUpdateLangPack update, boolean gettingDifferences){
        this.onTLUpdateLangPackCustom(update);
    }
    
    private void onTLUpdateLangPackTooLong(TLUpdateLangPackTooLong update, boolean gettingDifferences){
        this.onTLUpdateLangPackTooLongCustom(update);
    }
    
    private void onTLUpdateLoginToken(TLUpdateLoginToken update, boolean gettingDifferences){
        this.onTLUpdateLoginTokenCustom(update);
    }
    
    private void onTLUpdateMessageId(TLUpdateMessageId update) {
        this.onTLUpdateMessageIdCustom(update);
    }

    private void onTLUpdateMessagePoll(TLUpdateMessagePoll update) {
        this.onTLUpdateMessagePollCustom(update);
    }

    private void onTLUpdateNewMessage(TLUpdateNewMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserFromMessageMissing(update.getMessage())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateNewMessageCustom(update);
        }
    }

    private void onTLUpdateNewScheduledMessage(TLUpdateNewScheduledMessage update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserFromMessageMissing(update.getMessage())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateNewScheduledMessageCustom(update);
        }
    }

    private void onTLUpdateNewStickerSet(TLUpdateNewStickerSet update) {
        this.onTLUpdateNewStickerSetCustom(update);
    }

    private void onTLUpdateNotifySettings(TLUpdateNotifySettings update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isNotifyPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateNotifySettingsCustom(update);
        }
    }

    private void onTLUpdatePeerLocated(TLUpdatePeerLocated update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isAnyPeerMissing(update.getPeers())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdatePeerLocatedCustom(update);
        }
    }

    private void onTLUpdatePeerSettings(TLUpdatePeerSettings update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdatePeerSettingsCustom(update);
        }
    }

    private void onTLUpdatePhoneCall(TLUpdatePhoneCall update) {
        this.onTLUpdatePhoneCallCustom(update);
    }

    private void onTLUpdatePinnedDialogs(TLUpdatePinnedDialogs update) {
        this.onTLUpdatePinnedDialogsCustom(update);
    }

    private void onTLUpdatePrivacy(TLUpdatePrivacy update) {
        this.onTLUpdatePrivacyCustom(update);
    }

    private void onTLUpdatePtsChanged(TLUpdatePtsChanged update) {
        this.onTLUpdatePtsChangedCustom(update);
    }

    private void onTLUpdateReadChannelInbox(TLUpdateReadChannelInbox update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateReadChannelInboxCustom(update);
        }
    }

    private void onTLUpdateReadChannelOutbox(TLUpdateReadChannelOutbox update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isChatMissing(update.getChannelId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateReadChannelOutboxCustom(update);
        }
    }

    private void onTLUpdateReadFeaturedStickers(TLUpdateReadFeaturedStickers update) {
        this.onTLUpdateReadFeaturedStickersCustom(update);
    }

    private void onTLUpdateReadHistoryInbox(TLUpdateReadHistoryInbox update, boolean gettingDifferences) {
        this.onTLUpdateReadHistoryInboxCustom(update);
    }

    private void onTLUpdateReadMessagesContents(TLUpdateReadMessagesContents update) {
        this.onTLUpdateReadMessagesContentsCustom(update);
    }

    private void onTLUpdateReadMessagesInbox(TLUpdateReadMessagesInbox update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateReadMessagesInboxCustom(update);
        }
    }

    private void onTLUpdateReadMessagesOutbox(TLUpdateReadMessagesOutbox update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isPeerMissing(update.getPeer())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateReadMessagesOutboxCustom(update);
        }
    }

    private void onTLUpdateRecentStickers(TLUpdateRecentStickers update) {
        this.onTLUpdateRecentStickersCustom(update);
    }

    private void onTLUpdateSavedGifs(TLUpdateSavedGifs update) {
        this.onTLUpdateSavedGifsCustom(update);
    }

    private void onTLUpdateServiceNotification(TLUpdateServiceNotification update) {
        this.onTLUpdateServiceNotificationCustom(update);
    }

    private void onTLUpdateStickerSets(TLUpdateStickerSets update) {
        this.onTLUpdateStickerSetsCustom(update);
    }

    private void onTLUpdateStickerSetsOrder(TLUpdateStickerSetsOrder update) {
        this.onTLUpdateStickerSetsOrderCustom(update);
    }

    private void onTLUpdateTheme(TLUpdateTheme update, boolean gettingDifferences) {
        this.onTLUpdateThemeCustom(update);
    }

    private void onTLUpdateUserBlocked(TLUpdateUserBlocked update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateUserBlockedCustom(update);
        }
    }

    private void onTLUpdateUserName(TLUpdateUserName update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateUserNameCustom(update);
        }
    }

    private void onTLUpdateUserPhone(TLUpdateUserPhone update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateUserPhoneCustom(update);
        }
    }

    private void onTLUpdateUserPhoto(TLUpdateUserPhoto update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateUserPhotoCustom(update);
        }
    }

    private void onTLUpdateUserPinnedMessage(TLUpdateUserPinnedMessage update, boolean gettingDifferences) {
        this.onTLUpdateUserPinnedMessageCustom(update);
    }

    private void onTLUpdateUserStatus(TLUpdateUserStatus update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateUserStatusCustom(update);
        }
    }

    private void onTLUpdateUserTyping(TLUpdateUserTyping update, boolean gettingDifferences) {
        if (this.config.getDatabaseManager().isUserMissing(update.getUserId())) {
            if (!gettingDifferences) {
                this.config.getDifferencesHandler().getDifferences();
            }
        } else {
            this.onTLUpdateUserTypingCustom(update);
        }
    }

    private void onTLUpdateWebPage(TLUpdateWebPage update) {
        this.onTLUpdateWebPageCustom(update);
    }

    /** *****************************
     *  *****************************
     *       Update.encrypted
     *  *****************************
     ** *****************************/

    private void onTLUpdateEncryptedChatTyping(TLUpdateEncryptedChatTyping update) {
        this.onTLUpdateEncryptedChatTypingCustom(update);
    }

    private void onTLUpdateEncryption(TLUpdateEncryption update){
        this.onTLUpdateEncryptionCustom(update);
    }

    private void onTLUpdateEncryptedMessagesRead(TLUpdateEncryptedMessagesRead update) {
        this.onTLUpdateEncryptedMessagesReadCustom(update);
    }

    private void onTLUpdateNewEncryptedMessage(TLUpdateNewEncryptedMessage update) {
        this.onTLUpdateNewEncryptedMessageCustom(update);
    }

    /*
     * 
     */
    protected abstract void onTLFakeUpdateCustom(TLFakeUpdate update);
    protected abstract void onTLUpdateBotCallbackQueryCustom(TLUpdateBotCallbackQuery update);
    protected abstract void onTLUpdateBotInlineQueryCustom(TLUpdateBotInlineQuery update);
    protected abstract void onTLUpdateBotInlineSendCustom(TLUpdateBotInlineSend update);
    protected abstract void onTLUpdateBotPrecheckoutQueryCustom(TLUpdateBotPrecheckoutQuery update);
    protected abstract void onTLUpdateBotShippingQueryCustom(TLUpdateBotShippingQuery update);
    protected abstract void onTLUpdateBotWebhookJSONCustom(TLUpdateBotWebhookJSON update);
    protected abstract void onTLUpdateBotWebhookJSONQueryCustom(TLUpdateBotWebhookJSONQuery update);
    protected abstract void onTLUpdateChannelCustom(TLUpdateChannel update);
    protected abstract void onTLUpdateChannelAvailableMessagesCustom(TLUpdateChannelAvailableMessages update);
    protected abstract void onTLUpdateChannelMessageViewsCustom(TLUpdateChannelMessageViews update);
    protected abstract void onTLUpdateChannelNewMessageCustom(TLUpdateChannelNewMessage update);
    protected abstract void onTLUpdateChannelPinnedMessageCustom(TLUpdateChannelPinnedMessage update);
    protected abstract void onTLUpdateChannelReadMessagesContentsCustom(TLUpdateChannelReadMessagesContents update);
    protected abstract void onTLUpdateChannelWebPageCustom(TLUpdateChannelWebPage update);
    protected abstract void onTLUpdateChatDefaultBannedRightsCustom(TLUpdateChatDefaultBannedRights update);
    protected abstract void onTLUpdateChatParticipantAddCustom(TLUpdateChatParticipantAdd update);
    protected abstract void onTLUpdateChatParticipantAdminCustom(TLUpdateChatParticipantAdmin update);
    protected abstract void onTLUpdateChatParticipantDeleteCustom(TLUpdateChatParticipantDelete update);
    protected abstract void onTLUpdateChatParticipantsCustom(TLUpdateChatParticipants update);
    protected abstract void onTLUpdateChatPinnedMessageCustom(TLUpdateChatPinnedMessage update);
    protected abstract void onTLUpdateChatUserTypingCustom(TLUpdateChatUserTyping update);
    protected abstract void onTLUpdateConfigCustom(TLUpdateConfig update);
    protected abstract void onTLUpdateContactsResetCustom(TLUpdateContactsReset update);
    protected abstract void onTLUpdateDcOptionsCustom(TLUpdateDcOptions update);
    protected abstract void onTLUpdateDeleteChannelMessagesCustom(TLUpdateDeleteChannelMessages update);
    protected abstract void onTLUpdateDeleteMessagesCustom(TLUpdateDeleteMessages update);
    protected abstract void onTLUpdateDeleteScheduledMessageCustom(TLUpdateDeleteScheduledMessage update);
    protected abstract void onTLUpdateDialogPinnedCustom(TLUpdateDialogPinned update);
    protected abstract void onTLUpdateDialogUnreadMarkCustom(TLUpdateDialogUnreadMark update);
    protected abstract void onTLUpdateDraftMessageCustom(TLUpdateDraftMessage update);
    protected abstract void onTLUpdateEditChannelMessageCustom(TLUpdateEditChannelMessage update);
    protected abstract void onTLUpdateEditMessageCustom(TLUpdateEditMessage update);
    protected abstract void onTLUpdateFavedStickerCustom(TLUpdateFavedSticker update);
    protected abstract void onTLUpdateFolderPeersCustom(TLUpdateFolderPeers update);
    protected abstract void onTLUpdateGeoLiveViewedCustom(TLUpdateGeoLiveViewed update);
    protected abstract void onTLUpdateInlineBotCallbackQueryCustom(TLUpdateInlineBotCallbackQuery update);
    protected abstract void onTLUpdateLangPackCustom(TLUpdateLangPack update);
    protected abstract void onTLUpdateLangPackTooLongCustom(TLUpdateLangPackTooLong update);
    protected abstract void onTLUpdateLoginTokenCustom(TLUpdateLoginToken update);
    protected abstract void onTLUpdateMessageIdCustom(TLUpdateMessageId update);
    protected abstract void onTLUpdateMessagePollCustom(TLUpdateMessagePoll update);
    protected abstract void onTLUpdateNewMessageCustom(TLUpdateNewMessage update);
    protected abstract void onTLUpdateNewScheduledMessageCustom(TLUpdateNewScheduledMessage update);
    protected abstract void onTLUpdateNewStickerSetCustom(TLUpdateNewStickerSet update);
    protected abstract void onTLUpdateNotifySettingsCustom(TLUpdateNotifySettings update);
    protected abstract void onTLUpdatePeerLocatedCustom(TLUpdatePeerLocated update);
    protected abstract void onTLUpdatePeerSettingsCustom(TLUpdatePeerSettings update);
    protected abstract void onTLUpdatePhoneCallCustom(TLUpdatePhoneCall update);
    protected abstract void onTLUpdatePinnedDialogsCustom(TLUpdatePinnedDialogs update);
    protected abstract void onTLUpdatePrivacyCustom(TLUpdatePrivacy update);
    protected abstract void onTLUpdatePtsChangedCustom(TLUpdatePtsChanged update);
    protected abstract void onTLUpdateReadChannelInboxCustom(TLUpdateReadChannelInbox update);
    protected abstract void onTLUpdateReadChannelOutboxCustom(TLUpdateReadChannelOutbox update);
    protected abstract void onTLUpdateReadFeaturedStickersCustom(TLUpdateReadFeaturedStickers update);
    protected abstract void onTLUpdateReadHistoryInboxCustom(TLUpdateReadHistoryInbox update);
    protected abstract void onTLUpdateReadMessagesContentsCustom(TLUpdateReadMessagesContents update);
    protected abstract void onTLUpdateReadMessagesInboxCustom(TLUpdateReadMessagesInbox update);
    protected abstract void onTLUpdateReadMessagesOutboxCustom(TLUpdateReadMessagesOutbox update);
    protected abstract void onTLUpdateRecentStickersCustom(TLUpdateRecentStickers update);
    protected abstract void onTLUpdateSavedGifsCustom(TLUpdateSavedGifs update);
    protected abstract void onTLUpdateServiceNotificationCustom(TLUpdateServiceNotification update);
    protected abstract void onTLUpdateStickerSetsCustom(TLUpdateStickerSets update);
    protected abstract void onTLUpdateStickerSetsOrderCustom(TLUpdateStickerSetsOrder update);
    protected abstract void onTLUpdateThemeCustom(TLUpdateTheme update);
    protected abstract void onTLUpdateUserBlockedCustom(TLUpdateUserBlocked update);
    protected abstract void onTLUpdateUserNameCustom(TLUpdateUserName update);
    protected abstract void onTLUpdateUserPhoneCustom(TLUpdateUserPhone update);
    protected abstract void onTLUpdateUserPhotoCustom(TLUpdateUserPhoto update);
    protected abstract void onTLUpdateUserPinnedMessageCustom(TLUpdateUserPinnedMessage update);
    protected abstract void onTLUpdateUserStatusCustom(TLUpdateUserStatus update);
    protected abstract void onTLUpdateUserTypingCustom(TLUpdateUserTyping update);
    protected abstract void onTLUpdateWebPageCustom(TLUpdateWebPage update);
    //*****************//
    protected abstract void onTLUpdateEncryptedChatTypingCustom(TLUpdateEncryptedChatTyping update);
    protected abstract void onTLUpdateEncryptedMessagesReadCustom(TLUpdateEncryptedMessagesRead update);
    protected abstract void onTLUpdateEncryptionCustom(TLUpdateEncryption update);
    protected abstract void onTLUpdateNewEncryptedMessageCustom(TLUpdateNewEncryptedMessage update);
    //***----***//
    protected abstract void onTLUpdateShortCustom(TLUpdateShort update);
    protected abstract void onTLUpdateShortMessageCustom(TLUpdateShortMessage update);
    protected abstract void onTLUpdateShortChatMessageCustom(TLUpdateShortChatMessage update);
    protected abstract void onTLUpdateShortSentMessageCustom(TLUpdateShortSentMessage update);

}