package org.javagram.api.messages.base.dialogs;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.dialog.base.TLAbsDialog;
import org.javagram.api._primitives.TLVector;

/**
 * Full list of chats with messages and auxiliary data.
 * messages.dialogs#15ba6c40 dialogs:Vector&lt;Dialog&gt; messages:Vector&lt;Message&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = messages.Dialogs;
 */
public class TLDialogs extends TLAbsDialogs {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x15ba6c40;

    /**
     * List of chats
     */
    private TLVector<TLAbsDialog> dialogs;

    /**
     * List of last messages from each chat
     */
    private TLVector<TLAbsMessage> messages;

    /**
     * List of groups mentioned in the chats
     */
    private TLVector<TLAbsChat> chats;

    /**
     * List of users mentioned in messages and groups
     */
    private TLVector<TLAbsUser> users;

    public TLDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsDialog> getDialogs() {
        return this.dialogs;
    }

    public void setDialogs(TLVector<TLAbsDialog> value) {
        this.dialogs = value;
    }

    public TLVector<TLAbsMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(TLVector<TLAbsMessage> value) {
        this.messages = value;
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
        StreamingUtils.writeTLVector(this.dialogs, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.dialogs = StreamingUtils.readTLVector(stream, context, TLAbsDialog.class);
        this.messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.dialogs#15ba6c40";
    }
    
}