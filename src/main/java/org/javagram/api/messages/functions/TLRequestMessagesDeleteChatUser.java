package org.javagram.api.messages.functions;

import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Deletes a user from a chat and sends a service message on it.
 * messages.deleteChatUser#e0611f16 chat_id:int user_id:InputUser = Updates;
 */
public class TLRequestMessagesDeleteChatUser extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe0611f16;

    /**
     * Chat ID
     */
    private int chatId;
    
    /**
     * User ID to be deleted
     */
    private TLAbsInputUser userId;

    public TLRequestMessagesDeleteChatUser() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public TLAbsInputUser getUserId() {
        return this.userId;
    }

    public void setUserId(TLAbsInputUser value) {
        this.userId = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.deleteChatUser#e0611f16";
    }

}