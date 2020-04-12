package org.javagram.client.kernel;

import org.javagram.client.handlers.DefaultKernelHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.javagram.client.structure.TelegramFunctionCallback;
import org.javagram.utils.NotificationsService;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.util.concurrent.ExecutionException;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.input.media.TLAbsInputMedia;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.RpcException;
import org.javagram.utils.file.Downloader;
import org.javagram.utils.file.Uploader;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Interface for the helper class to perform Telegram API request
 * @date 22 of March of 2016
 */
public interface IKernelComm extends NotificationsService.NotificationObserver {

    <T extends TLObject> T doRpcCallSync(TLMethod<T> method) throws ExecutionException, RpcException;

    <T extends TLObject> T doRpcCallSyncNoAuth(TLMethod<T> method) throws ExecutionException, RpcException;

    <T extends TLObject> void doRpcCallAsync(TLMethod<T> method, TelegramFunctionCallback<T> callback);

    void doRpcCallAsyncNoReturn(TLMethod<TLObject> method);

    void sendMessage(@NotNull TLAbsUser user, @NotNull String message) throws RpcException;

    void sendMessageWithMarkdown(@NotNull TLAbsUser user, @NotNull String message) throws RpcException;

    void sendMessageWithEntities(@NotNull TLAbsUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException;

    void sendMessage(@NotNull TLAbsUser user, @NotNull String message, @NotNull Boolean hasWebPreview) throws RpcException;

    void sendMessage(@NotNull TLAbsUser user, @Nullable String message, @Nullable Integer replayToMsg,
                     @Nullable TLVector<TLAbsMessageEntity> entities,
                     boolean enableWebPreview, boolean parseMarkdown) throws RpcException;

    void sendMessageAsync(@NotNull TLAbsUser user, @NotNull String message, @NotNull Boolean hasWebPreview, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageAsync(@NotNull TLAbsUser user, @NotNull String message, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageAsync(@NotNull TLAbsUser user, @Nullable String message, @Nullable Integer replayToMsg,
                          @Nullable TLVector<TLAbsMessageEntity> entities,
                          boolean enableWebPreview, boolean parseMarkdown, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageWithEntitiesAsync(@NotNull TLAbsUser user, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageAsReply(@NotNull TLAbsUser user, @NotNull String message, @NotNull Integer replayToMsg) throws RpcException;

    void sendMessageAsReplyAsync(@NotNull TLAbsUser user, @NotNull String message, @NotNull Integer replayToMsg, TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendMessageWithoutPreview(@NotNull TLAbsUser user, @NotNull String message) throws RpcException;

    void sendMessageWithoutPreviewAsync(@NotNull TLAbsUser user, @NotNull String message, @Nullable TelegramFunctionCallback<TLAbsUpdates> callback);

    void sendGroupMessage(@NotNull TLAbsChat group, @NotNull String message) throws RpcException;

    void sendGroupMessage(@NotNull TLAbsChat group, @Nullable String message, @Nullable Integer replayToMsg,
                          @Nullable TLVector<TLAbsMessageEntity> entities,
                          boolean enableWebPreview, boolean parseMarkdown) throws RpcException;

    void sendGroupMessageWithMarkdown(@NotNull TLAbsChat group, @NotNull String message) throws RpcException;

    void sendGroupMessageWithEntities(@NotNull TLAbsChat group, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities) throws RpcException;

    void sendGroupMessageWithoutPreview(@NotNull TLAbsChat group, @NotNull String message) throws RpcException;

    void sendChannelMessage(@NotNull TLAbsChat channel, @NotNull String message, boolean asAdmin) throws RpcException;

    void sendChannelMessage(@NotNull TLAbsChat channel, @Nullable String message, @Nullable Integer replayToMsg,
                            @Nullable TLVector<TLAbsMessageEntity> entities,
                            boolean enableWebPreview, boolean parseMarkdown, boolean asAdmin)
            throws RpcException;
    void sendChannelMessageWithMarkdown(@NotNull TLAbsChat channel, @NotNull String message, boolean asAdmin) throws RpcException;

    void sendChannelMessageWithEntities(@NotNull TLAbsChat channel, @NotNull String message, @NotNull TLVector<TLAbsMessageEntity> entities, boolean asAdmin) throws RpcException;

    void sendChannelMessageWithoutPreview(@NotNull TLAbsChat channel, @NotNull String message, boolean asAdmin) throws RpcException;

    void sendMedia(@NotNull TLAbsUser user, @NotNull TLAbsInputMedia media) throws RpcException;

    void sendGroupMedia(@NotNull TLAbsChat group, @NotNull TLAbsInputMedia media) throws RpcException;

    void sendUploadedSticker(@NotNull String title, @NotNull String mimetype, @NotNull TLAbsUser user, long idFile, int parts) throws RpcException;

    void sendUploadedGroupSticker(@NotNull String title, @NotNull String mimetype, @NotNull TLAbsChat group, long idFile, int parts) throws RpcException;

    void performMarkAsRead(@NotNull TLAbsUser user, int messageId) throws RpcException;

    void performMarkGroupAsRead(@NotNull TLAbsChat group, int messageId) throws RpcException;

    int getCurrentUserId();

    Downloader getDownloader();

    Uploader getUploader();

}
