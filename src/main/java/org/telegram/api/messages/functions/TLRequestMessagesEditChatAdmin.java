package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Make a user admin in a @see <a href="https://core.telegram.org/api/channel">legacy group</a>.
 * 
 */
public class TLRequestMessagesEditChatAdmin extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa9e69f2e;

    /**
     * The ID of the group
     */
    private int chatId;

    /**
     * The user to make admin
     */
    private TLAbsInputPeer userId;

    /**
     * Whether to make him admin
     */
    private boolean isAdmin;

    public TLRequestMessagesEditChatAdmin() {
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

    public TLAbsInputPeer getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputPeer userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLObject(userId, stream);
        StreamingUtils.writeTLBool(this.isAdmin, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.isAdmin = StreamingUtils.readTLBool(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.editChatAdmin#a9e69f2e";
    }

}