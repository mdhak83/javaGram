package org.javagram.client.kernel;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.javagram.api.input.media.TLAbsInputMedia;
import org.javagram.api.input.media.TLInputMediaUploadedDocument;
import org.javagram.client.structure.GenericErrorTelegramFunctionCallback;
import org.javagram.client.structure.TelegramFunctionCallback;
import org.javagram.utils.TLFactory;
import org.javagram.utils.BotLogger;
import org.javagram.utils.NotificationsService;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomUtils;
import org.javagram.api.channel.base.input.TLInputChannel;
import org.javagram.api.channels.functions.TLRequestChannelsReadHistory;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.document.base.attribute.TLAbsDocumentAttribute;
import org.javagram.api.document.base.attribute.TLDocumentAttributeFilename;
import org.javagram.api.document.base.attribute.TLDocumentAttributeSticker;
import org.javagram.api.file.base.input.TLInputFile;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api.message.base.entity.TLMessageEntityBold;
import org.javagram.api.message.base.entity.TLMessageEntityCode;
import org.javagram.api.message.base.entity.TLMessageEntityItalic;
import org.javagram.api.messages.base.TLAffectedHistory;
import org.javagram.api.messages.base.TLAffectedMessages;
import org.javagram.api.messages.functions.TLRequestMessagesReadHistory;
import org.javagram.api.messages.functions.TLRequestMessagesSendMedia;
import org.javagram.api.messages.functions.TLRequestMessagesSendMessage;
import org.javagram.api.payments.base.result.TLPaymentsPaymentResult;
import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.peer.base.input.TLInputPeerChannel;
import org.javagram.api.sticker.base.input.set.TLInputStickerSetEmpty;
import org.javagram.api.update.base.TLFakeUpdate;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.api.updates.base.TLUpdateShort;
import org.javagram.api.updates.functions.TLRequestUpdatesGetState;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.MyTLAppConfiguration;
import org.javagram.utils.RpcException;
import org.javagram.utils.TimeoutException;
import org.javagram.utils.file.Downloader;
import org.javagram.utils.file.Uploader;

public class KernelCommunicationService implements IKernelComm {

    private static final String LOGTAG = "[KernelCommunicationService]";
    private static final int RANDOM_BYTES_COUNT = 1024;
    private static final Pattern REGEX_MARKDOWN_BOLD = Pattern.compile("\\*.+?\\*");
    private static final Pattern REGEX_MARKDOWN_ITALIC =  Pattern.compile("\\_.+?\\_");
    private static final Pattern REGEX_MARKDOWN_CODE = Pattern.compile("\\`.+?\\`");

    private MyTLAppConfiguration config;
    private final SecureRandom random = new SecureRandom();

    public KernelCommunicationService() {
        BotLogger.info(LOGTAG, "KernelCommunicationService created");
    }
    
    public void build(MyTLAppConfiguration config) {
        BotLogger.debug(LOGTAG, "Building KernelCommunicationService");
        this.config = config;
        if (this.config.getExecutor() == null) {
            this.config.setExecutor(Executors.newCachedThreadPool());
        }
        this.random.setSeed(RandomUtils.nextBytes(RANDOM_BYTES_COUNT));
        NotificationsService.getInstance().addObserver(this, NotificationsService.updatesInvalidated);
        BotLogger.debug(LOGTAG, "KernelCommunicationService built");
    }
    
    public void handleUpdates(TLAbsUpdates updates) {
        if (this.config.getKernelHandler() != null) {
            this.config.getKernelHandler().onUpdate(updates);
        } else {
            BotLogger.severe(LOGTAG, "Main Handler not found from kernelcomm");
        }
    }

    private void handleFakeUpdate(int pts, int ptsCount) {
        final TLFakeUpdate fakeUpdate = new TLFakeUpdate();
        fakeUpdate.setPts(pts);
        fakeUpdate.setPtsCount(ptsCount);
        final TLUpdateShort updateShort = new TLUpdateShort();
        updateShort.setDate(0);
        updateShort.setUpdate(fakeUpdate);
        this.config.getKernelHandler().onUpdate(updateShort);
    }

    private void handleAffectedMessagesAndHistory(TLObject object) {
        if (object instanceof TLAffectedHistory) {
            handleFakeUpdate(((TLAffectedHistory) object).getPts(), ((TLAffectedHistory) object).getPtsCount());
        } else if (object instanceof TLAffectedMessages) {
            handleFakeUpdate(((TLAffectedMessages) object).getPts(), ((TLAffectedMessages) object).getPtsCount());
        } else if (object instanceof TLPaymentsPaymentResult) {
            this.config.getKernelHandler().onUpdate(((TLPaymentsPaymentResult) object).getUpdates());
        }
    }

    @Override
    public <T extends TLObject> T doRpcCallSync(final TLMethod<T> method) throws ExecutionException, RpcException {
        T answer = null;
        if (this.config.getApi() != null) {
            final Future<T> result = this.config.getExecutor().submit(() -> {
                T internalAnswer = null;
                try {
                    internalAnswer = this.config.getApi().doRpcCall(method);
                } catch (RpcException e) {
                    BotLogger.debug(LOGTAG, "Rpc call failed", e);
                    throw e;
                } catch (TimeoutException e) {
                    BotLogger.debug(LOGTAG, "timeout");
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
                }

                return internalAnswer;
            });
            try {
                answer = result.get();
            } catch (InterruptedException e) {
                BotLogger.error(LOGTAG, e);
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
            }
        }

        handleAffectedMessagesAndHistory(answer);
        return answer;
    }

    @Override
    public <T extends TLObject> T doRpcCallSyncNoAuth(final TLMethod<T> method) throws ExecutionException {
        T answer = null;
        final Future<T> result = this.config.getExecutor().submit(() -> {
            T answerInternal = null;
            try {
                answerInternal = this.config.getApi().doRpcCallNonAuth(method);
            } catch (RpcException e) {
                BotLogger.error(LOGTAG, "Rpc call failed", e);
                throw e;
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSyncNoAuth");
            }
            return answerInternal;
        });
        try {
            answer = result.get();
        } catch (InterruptedException e) {
            BotLogger.error(LOGTAG, e);
        } catch (Exception e) {
            BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSyncNoAuth");
        }

        handleAffectedMessagesAndHistory(answer);
        return answer;
    }

    @Override
    public <T extends TLObject> void doRpcCallAsync(final TLMethod<T> method, TelegramFunctionCallback<T> callback) {
        if (this.config.getApi() != null) {
            final CompletableFuture<T> result = CompletableFuture.supplyAsync(() -> {
                T internalAnswer = null;
                try {
                    internalAnswer = this.config.getApi().doRpcCall(method);
                    handleAffectedMessagesAndHistory(internalAnswer);
                } catch (RpcException e) {
                    BotLogger.debug(LOGTAG, "Rpc call failed", e);
                    callback.onRpcError(e);
                } catch (TimeoutException e) {
                    BotLogger.debug(LOGTAG, "timeout");
                    callback.onTimeout(e);
                } catch (Exception e) {
                    BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallSync", e);
                    callback.onUnknownError(e);
                }

                return internalAnswer;
            });
            result.handleAsync((ok, ex) -> {
                if (ok != null) {
                    callback.onSuccess(ok);
                } else {
                    callback.onUnknownError(ex);
                }
                return null;
            });
        }
    }

    @Override
    public void doRpcCallAsyncNoReturn(final TLMethod<TLObject> method) {
        this.config.getExecutor().submit(() -> {
            try {
                final TLObject answer = this.config.getApi().doRpcCall(method);
                handleAffectedMessagesAndHistory(answer);
            } catch (RpcException e) {
                BotLogger.severe(LOGTAG, "Rpc call failed", e);
            } catch (TimeoutException e) {
                BotLogger.severe(LOGTAG, "timeout");
            } catch (IOException e) {
                BotLogger.error(LOGTAG, e);
            } catch (Exception e) {
                BotLogger.error(LOGTAG, "Bot threw an unexpected exception at KernelComm-doRpcCallAsyncNoReturn");
            }
        });
    }

    private void sendMessageInternal(@NotNull TLAbsUser user, @Nullable String message, @Nullable Integer replayToMsg,
                                     @Nullable TLVector<TLAbsMessageEntity> entities,
                                     boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();
        request.setPeer(TLFactory.createTLInputPeer(user, null));

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending message " + replayToMsg + " as reply to: " + user.getId());
            request.setReplyToMsgId(replayToMsg);
        }
        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending message without preview to: " + user.getId());
            request.setNoWebpage(true);
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            BotLogger.info(LOGTAG, "Sending message to: " + user.getId());
            performSendMessageSyncInternal(request);
        }
    }

    private void sendMessageInternalAsync(@NotNull TLAbsUser user, @Nullable String message, @Nullable Integer replayToMsg,
                                          @Nullable TLVector<TLAbsMessageEntity> entities,
                                          boolean enableWebPreview, boolean parseMarkdown, TelegramFunctionCallback<TLAbsUpdates> callback) {
        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();
        request.setPeer(TLFactory.createTLInputPeer(user, null));

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending message " + replayToMsg + " as reply to: " + user.getId());
            request.setReplyToMsgId(replayToMsg);
        }
        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending message without preview to: " + user.getId());
            request.setNoWebpage(true);
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            BotLogger.info(LOGTAG, "Sending message to: " + user.getId());
            final int id = this.random.nextInt();
            request.setRandomId(id);

            doRpcCallAsync(request, callback);
        }
    }

    private void sendMessageGroupInternal(@NotNull TLAbsChat group, @Nullable String message, @Nullable Integer replayToMsg,
                                          @Nullable TLVector<TLAbsMessageEntity> entities,
                                          boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();
        request.setPeer(TLFactory.createTLInputPeer(null, group));

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending group message " + replayToMsg + " as reply to: " + group.getId());
            request.setReplyToMsgId(replayToMsg);
        }

        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending group message without preview to: " + group.getId());
            request.setNoWebpage(true);
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            BotLogger.info(LOGTAG, "Sending message to: " + group.getId());
            performSendMessageSyncInternal(request);
        }
    }

    private void sendMessageChannelInternal(@NotNull TLAbsChat channel, @Nullable String message, @Nullable Integer replayToMsg,
                                            @Nullable TLVector<TLAbsMessageEntity> entities,
                                            boolean enableWebPreview, boolean parseMarkdown, boolean silent)
            throws RpcException {

        boolean canSend = false;
        final TLRequestMessagesSendMessage request = new TLRequestMessagesSendMessage();

        if ((message != null) && !message.isEmpty()) {
            if (parseMarkdown) {
                if (entities == null) {
                    entities = new TLVector<>();
                }
                message = readEntities(message, entities);
            }
            request.setMessage(message);
            canSend = true;
        }

        request.setSilent(silent);

        if (replayToMsg != null) {
            BotLogger.info(LOGTAG, "Sending channel message " + replayToMsg + " as reply to: " + channel.getId());
            request.setReplyToMsgId(replayToMsg);
        }
        if (!enableWebPreview) {
            BotLogger.info(LOGTAG, "Sending channel message without preview to: " + channel.getId());
            request.setNoWebpage(true);
        }

        if (entities != null && !entities.isEmpty()) {
            request.setEntities(entities);
        }

        if (canSend) {
            request.setPeer(TLFactory.createTLInputPeer(null, channel));
            BotLogger.info(LOGTAG, "Sending channel message to: " + channel.getId());
            performSendMessageSyncInternal(request);
        }
    }

    private void performSendMessageSyncInternal(TLRequestMessagesSendMessage request) throws RpcException {
        final int id = this.random.nextInt();
        request.setRandomId(id);

        try {
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.error(LOGTAG, e);
        } finally {
            BotLogger.info(LOGTAG, "Sending message " + id + " was successful");
        }
    }

    @Override
    public void sendMessage(@NotNull TLAbsUser user, @NotNull String message) throws RpcException {
        sendMessageInternal(user, message, null,null, false, false);
    }

    @Override
    public void sendMessage(@NotNull TLAbsUser user, @NotNull String message, @NotNull Boolean hasWebPreview) throws RpcException {
        sendMessageInternal(user, message, null, null,hasWebPreview, false);
    }

    @Override
    public void sendMessage(@NotNull TLAbsUser user, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        sendMessageInternal(user, message, replayToMsg, entities, enableWebPreview, parseMarkdown);
    }

    /**
     * Send a message without web preview
     * @param user Destination user
     * @param message Message to send
     * @param hasWebPreview If the message must have web preview
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by implementation messages list.
     */
    @Override
    public void sendMessageAsync(@NotNull TLAbsUser user, @NotNull String message, @NotNull Boolean hasWebPreview, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, null,hasWebPreview, false, callback);
    }

    /**
     * Send a message
     * @param user Destination user
     * @param message Message to send
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by implementation messages list.
     */
    @Override
    public void sendMessageAsync(@NotNull TLAbsUser user, @NotNull String message, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, null, true, false, callback);
    }

    @Override
    public void sendMessageAsReply(@NotNull TLAbsUser user, @NotNull String message, @NotNull Integer replayToMsg) throws RpcException {
        sendMessageInternal(user, message, replayToMsg, null, true, false);
    }

    @Override
    public void sendMessageAsync(@NotNull TLAbsUser user, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, replayToMsg, entities, enableWebPreview, parseMarkdown, callback);
    }

    /**
     * Send a message as reply
     * @param user Destination user
     * @param message Message to send
     * @param replayToMsg Id of the message to answer
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by implementation messages list.
     */
    @Override
    public void sendMessageAsReplyAsync(@NotNull TLAbsUser user, @NotNull String message, @NotNull Integer replayToMsg, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }

        sendMessageInternalAsync(user, message, replayToMsg, null,true, false, callback);
    }

    @Override
    public void sendMessageWithMarkdown(@NotNull TLAbsUser user, @NotNull String message) throws RpcException {
        sendMessageInternal(user, message, null, null,true, true);
    }

    @Override
    public void sendMessageWithEntities(@NotNull TLAbsUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException {
        sendMessageInternal(user, message, null, entities,true, true);
    }

    @Override
    public void sendMessageWithoutPreview(@NotNull TLAbsUser user, @NotNull String message) throws RpcException {
        sendMessageInternal(user, message, null, null,false, false);
    }

    /**
     * Send a message without web preview
     * @param user Destination user
     * @param message Message to send
     * @param callback Callback to execute after sent. If no provided, a default one will be executed.
     *
     * @note Default callback just send received updates to KernelHandler and add the id to the sent by bot messages list.
     */
    @Override
    public void sendMessageWithoutPreviewAsync(@NotNull TLAbsUser user, @NotNull String message, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, null, false, false, callback);
    }

    @Override
    public void sendMessageWithEntitiesAsync(@NotNull TLAbsUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, TelegramFunctionCallback<TLAbsUpdates> callback) {
        if (callback == null) {
            callback = getDefaultSendMessageCallback(user);
        }
        sendMessageInternalAsync(user, message, null, entities, false, false, callback);
    }

    @Override
    public void sendGroupMessage(@NotNull TLAbsChat group, @NotNull String message) throws RpcException {
        sendMessageGroupInternal(group, message, null, null, true, false);
    }

    @Override
    public void sendGroupMessage(@NotNull TLAbsChat group, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown) throws RpcException {
        sendMessageGroupInternal(group, message, replayToMsg, entities, enableWebPreview, parseMarkdown);
    }

    @Override
    public void sendGroupMessageWithMarkdown(@NotNull TLAbsChat group, @NotNull String message) throws RpcException {
        sendMessageGroupInternal(group, message, null, null, true, true);
    }

    @Override
    public void sendGroupMessageWithEntities(@NotNull TLAbsChat group, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException {
        sendMessageGroupInternal(group, message, null, entities, true, true);
    }

    @Override
    public void sendGroupMessageWithoutPreview(@NotNull TLAbsChat group, @NotNull String message) throws RpcException {
        sendMessageGroupInternal(group, message, null, null, false, false);
    }

    @Override
    public void sendChannelMessage(@NotNull TLAbsChat channel, @NotNull String message, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null,null, true, false, asAdmin);
    }

    @Override
    public void sendChannelMessage(@NotNull TLAbsChat channel, @Nullable String message, @Nullable Integer replayToMsg, @Nullable TLVector<TLAbsMessageEntity> entities, boolean enableWebPreview, boolean parseMarkdown, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, replayToMsg,entities, enableWebPreview, parseMarkdown, asAdmin);
    }

    @Override
    public void sendChannelMessageWithMarkdown(@NotNull TLAbsChat channel, @NotNull String message, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null, null, true, true, asAdmin);
    }

    @Override
    public void sendChannelMessageWithEntities(@NotNull TLAbsChat channel, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null, entities, true, true, asAdmin);
    }

    @Override
    public void sendChannelMessageWithoutPreview(@NotNull TLAbsChat channel, @NotNull String message, boolean asAdmin) throws RpcException {
        sendMessageChannelInternal(channel, message, null, null, false, false, asAdmin);
    }

    @Override
    public void sendMedia(@NotNull TLAbsUser user, @NotNull TLAbsInputMedia media) throws RpcException{
        final int id = this.random.nextInt();
        try {
            BotLogger.debug(LOGTAG, "Sending media " + id + " to: " + user + " : " + media);
            BotLogger.debug(LOGTAG, "Sending media " + id + " to: " + user);
            final TLRequestMessagesSendMedia request = new TLRequestMessagesSendMedia();
            request.setPeer(TLFactory.createTLInputPeer(user, null));
            request.setMedia(media);
            request.setRandomId(id);
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.warning(LOGTAG,"Sending media " + id + " failed");
        }
    }

    @Override
    public void sendGroupMedia(@NotNull TLAbsChat chat, @NotNull TLAbsInputMedia media) throws RpcException {
        final int id = this.random.nextInt();
        try {
            BotLogger.debug(LOGTAG, "Sending media " + id + " to group: " + chat.getId() + " : " + media);
            BotLogger.info(LOGTAG, "Sending media " + id + " to group: " + chat.getId());
            final TLRequestMessagesSendMedia request = new TLRequestMessagesSendMedia();
            request.setPeer(TLFactory.createTLInputPeer(null, chat));
            request.setMedia(media);
            request.setRandomId(id);
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.warning(LOGTAG, "Sending media " + id + " failed");
        }
    }

    @Override
    public void sendUploadedSticker(@NotNull String title, @NotNull String mimetype, @NotNull TLAbsUser user, long idFile, int parts) throws RpcException {
        final int id = this.random.nextInt();
        try {
            final TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>();
            final TLDocumentAttributeFilename fileName = new TLDocumentAttributeFilename();
            fileName.setFileName(title);
            attributes.add(fileName);
            final TLDocumentAttributeSticker sticker = new TLDocumentAttributeSticker();
            sticker.setAlt("");
            sticker.setStickerset(new TLInputStickerSetEmpty());
            attributes.add(sticker);
            final TLInputFile inputFile = new TLInputFile();
            inputFile.setId(idFile);
            inputFile.setMd5Checksum("");
            inputFile.setName(title);
            inputFile.setParts(parts);
            final TLInputMediaUploadedDocument inputMediaUploadedDocument = new TLInputMediaUploadedDocument();
            inputMediaUploadedDocument.setFile(inputFile);
            inputMediaUploadedDocument.setMimeType(mimetype);
            inputMediaUploadedDocument.setAttributes(attributes);
            sendMedia(user, inputMediaUploadedDocument);
        } finally {
            BotLogger.info(LOGTAG, "Sending file " + id + " to " + user.getId() + " was successful");
        }
    }

    @Override
    public void sendUploadedGroupSticker(@NotNull String title, @NotNull String mimetype, @NotNull TLAbsChat group, long idFile, int parts) throws RpcException {
        final int id = this.random.nextInt();
        try {
            final TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>();
            final TLDocumentAttributeFilename fileName = new TLDocumentAttributeFilename();
            fileName.setFileName(title);
            attributes.add(fileName);
            final TLDocumentAttributeSticker sticker = new TLDocumentAttributeSticker();
            sticker.setAlt("");
            sticker.setStickerset(new TLInputStickerSetEmpty());
            attributes.add(sticker);
            final TLInputFile inputFile = new TLInputFile();
            inputFile.setId(idFile);
            inputFile.setMd5Checksum("");
            inputFile.setName(title);
            inputFile.setParts(parts);
            final TLInputMediaUploadedDocument inputMediaUploadedDocument = new TLInputMediaUploadedDocument();
            inputMediaUploadedDocument.setFile(inputFile);
            inputMediaUploadedDocument.setMimeType(mimetype);
            inputMediaUploadedDocument.setAttributes(attributes);
            final TLRequestMessagesSendMedia request = new TLRequestMessagesSendMedia();
            request.setPeer(TLFactory.createTLInputPeer(null, group));
            request.setMedia(inputMediaUploadedDocument);
            request.setRandomId(id);
            final TLAbsUpdates updates = doRpcCallSync(request);
            if (updates != null) {
                handleUpdates(updates);
            }
        } catch (ExecutionException e) {
            BotLogger.error(LOGTAG, e);
            BotLogger.warning(LOGTAG, "Sending file " + id + " to group " + group.getId() + " failed");
        } finally {
            BotLogger.info(LOGTAG, "Sending file " + id + " to group " + group.getId() + " was successful");
        }
    }

    @Override
    public void performMarkAsRead(@NotNull TLAbsUser user, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeer(user, null), messageId);
    }

    @Override
    public void performMarkGroupAsRead(@NotNull TLAbsChat group, int messageId) throws RpcException {
        performMarkAsReadInternal(TLFactory.createTLInputPeer(null, group), messageId);
    }

    private void performMarkAsReadInternal(TLAbsInputPeer inputPeer, int messageId) throws RpcException {
        try {
            if (inputPeer instanceof TLInputPeerChannel) {
                final TLInputChannel inputChannel = new TLInputChannel();
                inputChannel.setChannelId(((TLInputPeerChannel) inputPeer).getChannelId());
                inputChannel.setAccessHash(((TLInputPeerChannel) inputPeer).getAccessHash());
                final TLRequestChannelsReadHistory tlRequestChannelsReadHistory = new TLRequestChannelsReadHistory();
                tlRequestChannelsReadHistory.setChannel(inputChannel);
                tlRequestChannelsReadHistory.setMaxId(messageId);
                doRpcCallSync(tlRequestChannelsReadHistory);
            } else {
                final TLRequestMessagesReadHistory tlRequestMessagesReadHistory = new TLRequestMessagesReadHistory();
                tlRequestMessagesReadHistory.setPeer(inputPeer);
                tlRequestMessagesReadHistory.setMaxId(messageId);
                doRpcCallSync(tlRequestMessagesReadHistory);
            }

        } catch (ExecutionException e) {
            BotLogger.severe(LOGTAG, e);
        }
    }

    @Override
    public int getCurrentUserId() {
        return this.config.getApiState().getUserId();
    }

    @NotNull
    private String readEntities(@NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) {
        message = readBoldEntities(entities, message);
        message = readItalicEntities(entities, message);
        message = readCodeEntities(entities, message);

        return message;
    }

    @NotNull
    private String readBoldEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = REGEX_MARKDOWN_BOLD.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityBold boldEntity = new TLMessageEntityBold();
            boldEntity.setOffset(initMarkdown);
            boldEntity.setLength(lastIndex - startIndex - 2);
            entities.add(boldEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @NotNull
    private String readItalicEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = REGEX_MARKDOWN_ITALIC.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityItalic italicEntity = new TLMessageEntityItalic();
            italicEntity.setOffset(initMarkdown);
            italicEntity.setLength(lastIndex - startIndex - 2);
            entities.add(italicEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @NotNull
    private String readCodeEntities(@NotNull TLVector<TLAbsMessageEntity> entities, @NotNull String message) {
        final StringBuilder finalMessage = new StringBuilder();
        int lastAddedIndex = 0;
        final Matcher matcher = REGEX_MARKDOWN_CODE.matcher(message);

        while (matcher.find()) {
            final int startIndex = matcher.start();
            final int lastIndex = matcher.end();
            finalMessage.append(message.substring(lastAddedIndex, startIndex));
            final int initMarkdown = finalMessage.length();
            finalMessage.append(message.substring(startIndex + 1, lastIndex-1));
            lastAddedIndex = lastIndex;
            final TLMessageEntityCode codeEntity = new TLMessageEntityCode();
            codeEntity.setOffset(initMarkdown);
            codeEntity.setLength(lastIndex - startIndex - 2);
            entities.add(codeEntity);
        }

        if (lastAddedIndex != message.length()) {
            finalMessage.append(message.substring(lastAddedIndex));
        }

        return finalMessage.toString();
    }

    @Override
    public void onNotificationReceived(int notificationId, Object... args) {
        if (notificationId == NotificationsService.updatesInvalidated) {
            if (args.length == 1) {
                try {
                    ((TelegramApi) args[0]).doRpcCall(new TLRequestUpdatesGetState());
                    BotLogger.debug(LOGTAG, "Updated recreated session");
                } catch (IOException | java.util.concurrent.TimeoutException e) {
                    BotLogger.error(LOGTAG, "Updating recreated session failed", e);
                }
                BotLogger.debug(LOGTAG, "Updated recreated session");
                NotificationsService.getInstance().postNotification(NotificationsService.needGetUpdates);
            }
        }
    }

    @Override
    public Downloader getDownloader() {
        return this.config.getApi().getDownloader();
    }

    @Override
    public Uploader getUploader() {
        return this.config.getApi().getUploader();
    }

    @Contract("_ -> !null")
    private TelegramFunctionCallback<TLAbsUpdates> getDefaultSendMessageCallback(final @NotNull TLAbsUser user) {
        return new GenericErrorTelegramFunctionCallback<TLAbsUpdates>() {
            @Override
            public void onError(Throwable e) {
                BotLogger.error(LOGTAG, "Failed sending message to: " + user.getId(), e);
            }

            @Override
            public void onSuccess(TLAbsUpdates result) {
                if (result != null) {
                    KernelCommunicationService.this.handleUpdates(result);
                }
            }
        };
    }

    @Override
    protected void finalize() throws Throwable {
        NotificationsService.getInstance().removeObserver(this, NotificationsService.updatesInvalidated);
        super.finalize();
    }
}