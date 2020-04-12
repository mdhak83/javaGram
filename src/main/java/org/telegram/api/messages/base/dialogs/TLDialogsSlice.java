package org.telegram.api.messages.base.dialogs;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.dialog.base.TLAbsDialog;
import org.telegram.api._primitives.TLVector;

/**
 * Incomplete list of dialogs with messages and auxiliary data.
 * messages.dialogsSlice#71e094f3 count:int dialogs:Vector&lt;Dialog&gt; messages:Vector&lt;Message&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = messages.Dialogs;
 */
public class TLDialogsSlice extends TLAbsDialogs {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x71e094f3;

    /**
     * Total number of dialogs
     */
    private int count;

    /**
     * List of dialogs
     */
    private TLVector<TLAbsDialog> dialogs;

    /**
     * List of last messages from dialogs
     */
    private TLVector<TLAbsMessage> messages;

    /**
     * List of chats mentioned in dialogs
     */
    private TLVector<TLAbsChat> chats;

    /**
     * List of users mentioned in messages and chats
     */
    private TLVector<TLAbsUser> users;

    public TLDialogsSlice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int value) {
        this.count = value;
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
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeTLVector(this.dialogs, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.count = StreamingUtils.readInt(stream);
        this.dialogs = StreamingUtils.readTLVector(stream, context, TLAbsDialog.class);
        this.messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.dialogsSlice#71e094f3";
    }
    
}