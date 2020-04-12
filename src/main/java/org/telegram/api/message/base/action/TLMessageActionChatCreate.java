package org.telegram.api.message.base.action;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLIntVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Group created
 * messageActionChatCreate#a6638b9a title:string users:Vector&lt;int&gt; = MessageAction;
 */
public class TLMessageActionChatCreate extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa6638b9a;

    /**
     * Group name
     */
    private String title;
    
    /**
     * List of group members
     */
    private TLIntVector users;

    public TLMessageActionChatCreate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLIntVector getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(TLIntVector users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.title = StreamingUtils.readTLString(stream);
        this.users = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "messageActionChatCreate#a6638b9a";
    }
    
}