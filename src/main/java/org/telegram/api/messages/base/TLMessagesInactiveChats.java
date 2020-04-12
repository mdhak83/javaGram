package org.telegram.api.messages.base;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

/**
 * Inactive chat list
 * messages.inactiveChats#a927fec5 dates:Vector&lt;int&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = messages.InactiveChats;
 */
public class TLMessagesInactiveChats extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa927fec5;

    /**
     * When was the chat last active
     */
    private TLVector<Integer> dates;

    /**
     * Chat list
     */
    private TLVector<TLAbsChat> chats;

    /**
     * Users mentioned in the chat list
     */
    private TLVector<TLAbsUser> users;

    public TLMessagesInactiveChats() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<Integer> getDates() {
        return this.dates;
    }

    public void setDates(TLVector<Integer> dates) {
        this.dates = dates;
    }

    public TLVector<TLAbsChat> getChats() {
        return this.chats;
    }

    public void setChats(TLVector<TLAbsChat> value) {
        this.chats = value;
    }

    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.dates, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dates = StreamingUtils.readTLIntVector(stream, context);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.inactiveChats#a927fec5";
    }
    
}