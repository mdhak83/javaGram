package org.telegram.api.updates.base.channel.differences;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.api.update.base.TLAbsUpdate;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;

/**
 * updates.ChannelDifference
 * The new updates
 * updates.channelDifference#2064674e flags:# final:flags.0?true pts:int timeout:flags.1?int new_messages:Vector&lt;Message&gt; other_updates:Vector&lt;Update&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = updates.ChannelDifference;
 */
public class TLUpdatesChannelDifference extends TLAbsUpdatesChannelDifference {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2064674e;

    /**
     * The @see <a href="https://core.telegram.org/api/updates">PTS</a> from which to start getting updates the next time
     */
    private int pts;

    /**
     * New messages
     */
    private TLVector<TLAbsMessage> newMessages;

    /**
     * Other updates
     */
    private TLVector<TLAbsUpdate> otherUpdates;

    /**
     * Chats
     */
    private TLVector<TLAbsChat> chats;

    /**
     * Users
     */
    private TLVector<TLAbsUser> users;

    public TLUpdatesChannelDifference() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public TLVector<TLAbsMessage> getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(TLVector<TLAbsMessage> newMessages) {
        this.newMessages = newMessages;
    }

    public TLVector<TLAbsUpdate> getOtherUpdates() {
        return otherUpdates;
    }

    public void setOtherUpdates(TLVector<TLAbsUpdate> otherUpdates) {
        this.otherUpdates = otherUpdates;
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if (this.hasTimeout()) {
            StreamingUtils.writeInt(this.timeout, stream);
        }
        StreamingUtils.writeTLVector(this.newMessages, stream);
        StreamingUtils.writeTLVector(this.otherUpdates, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.setPts(StreamingUtils.readInt(stream));
        if (this.hasTimeout()) {
            this.setTimeout(StreamingUtils.readInt(stream));
        }
        this.newMessages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.otherUpdates = StreamingUtils.readTLVector(stream, context, TLAbsUpdate.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "updates.channelDifference#2064674e";
    }

}