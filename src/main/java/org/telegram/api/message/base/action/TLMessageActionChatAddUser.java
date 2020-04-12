package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLIntVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * New member in the group
 * messageActionChatAddUser#488a7337 users:Vector&lt;int&gt; = MessageAction;
 */
public class TLMessageActionChatAddUser extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x488a7337;

    /**
     * Users that were invited to the chat
     */
    private TLIntVector users;

    public TLMessageActionChatAddUser() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLIntVector getUsers() {
        return users;
    }

    public void setUsers(TLIntVector users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.users = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "messageActionChatAddUser#488a7337";
    }

}