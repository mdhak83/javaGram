package org.javagram.api.message.base.action;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * User left the group.
 * messageActionChatDeleteUser#b2ae9b0c user_id:int = MessageAction;
 */
public class TLMessageActionChatDeleteUser extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb2ae9b0c;

    /**
     * Leaving user ID
     */
    private int userId;

    public TLMessageActionChatDeleteUser() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageActionChatDeleteUser#b2ae9b0c";
    }
    
}