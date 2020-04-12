package org.javagram.api.updates.base.channel.differences;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.dialog.base.TLAbsDialog;
import org.javagram.api.dialog.base.TLDialog;

/**
 * updates.channelDifferenceTooLong
 * The provided <code>pts + limit &lt; remote pts</code>.
 * Simply, there are too many updates to be fetched (more than limit), the client has to resolve the update gap in one of the following ways:
 * <ul>
 * <li>1. Delete all known messages in the chat, begin from scratch by refetching all messages manually with @see <a href="https://core.telegram.org/method/messages.getHistory">getHistory</a>. It is easy to implement, but suddenly disappearing messages looks awful for the user.</li>
 * <li>2. Save all messages loaded in the memory until application restart, but delete all messages from database. Messages left in the memory must be lazily updated using calls to @see <a href="https://core.telegram.org/method/messages.getHistory">getHistory</a>. It looks much smoothly for the user, he will need to redownload messages only after client restart. Unsynchronized messages left in the memory shouldn't be saved to database, results of @see <a href="https://core.telegram.org/method/messages.getHistory">getHistory</a> and @see <a href="https://core.telegram.org/method/messages.getMessages">getMessages</a> must be used to update state of deleted and edited messages left in the memory.</li>
 * <li>3. Save all messages loaded in the memory and stored in the database without saving that some messages form continuous ranges. Messages in the database will be excluded from results of getChatHistory and searchChatMessages after application restart and will be available only through getMessage. Every message should still be checked using getHistory. It has more disadvantages over 2) than advantages.</li>
 * <li>4. Save all messages with saving all data about continuous message ranges. Messages from the database may be used as results of getChatHistory and (if implemented continuous ranges support for searching shared media) searchChatMessages. The messages should still be lazily checked using getHistory, but they are still available offline. It is the best way for gaps support, but it is pretty hard to implement correctly. It should be also noted that some messages like live location messages shouldn't be deleted.</li>
 * </ul>
 * updates.channelDifferenceTooLong#a4bcc6fe flags:# final:flags.0?true timeout:flags.1?int dialog:Dialog messages:Vector&lt;Message&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = updates.ChannelDifference;
 */
public class TLUpdatesChannelDifferenceTooLong extends TLAbsUpdatesChannelDifference {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa4bcc6fe;

    /**
     * Dialog containing the latest @see <a href="https://core.telegram.org/api/updates">PTS</a> that can be used to reset the channel state
     */
    private TLAbsDialog dialog;

    /**
     * The latest messages
     */
    private TLVector<TLAbsMessage> messages;

    /**
     * Chats from messages
     */
    private TLVector<TLAbsChat> chats;

    /**
     * Users from messages
     */
    private TLVector<TLAbsUser> users;

    public TLUpdatesChannelDifferenceTooLong() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public int getPts() {
        return this.dialog != null ? (this.dialog instanceof TLDialog ? ((TLDialog) this.dialog).getPts() : 0) : 0;
    }
    
    public TLVector<TLAbsMessage> getMessages() {
        return messages;
    }

    public void setMessages(TLVector<TLAbsMessage> messages) {
        this.messages = messages;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    public TLAbsDialog getDialog() {
        return dialog;
    }

    public void setDialog(TLDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasTimeout()) {
            StreamingUtils.writeInt(this.timeout, stream);
        }
        StreamingUtils.writeTLObject(this.dialog, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasTimeout()) {
            this.timeout = StreamingUtils.readInt(stream);
        }
        this.dialog = StreamingUtils.readTLObject(stream, context, TLAbsDialog.class);
        this.messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "updates.channelDifferenceTooLong#a4bcc6fe";
    }

}