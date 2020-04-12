package org.javagram.api.updates.base;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.update.base.TLAbsUpdate;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL updates.
 */
public class TLUpdates extends TLAbsUpdates {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x74ae4240;

    /**
     * List of updates
     */
    private TLVector<TLAbsUpdate> updates;

    /**
     * List of users mentioned in updates
     */
    private TLVector<TLAbsUser> users;

    /**
     * List of chats mentioned in updates
     */
    private TLVector<TLAbsChat> chats;

    /**
     * Current date
     */
    private int date;

    /**
     * Total number of sent updates
     */
    private int seq;

    public TLUpdates() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsUpdate> getUpdates() {
        return this.updates;
    }

    public void setUpdates(TLVector<TLAbsUpdate> updates) {
        this.updates = updates;
    }

    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    public TLVector<TLAbsChat> getChats() {
        return this.chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.updates, stream);
        StreamingUtils.writeTLVector(this.users, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.seq, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.updates = StreamingUtils.readTLVector(stream, context, TLAbsUpdate.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.date = StreamingUtils.readInt(stream);
        this.seq = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updates#74ae4240";
    }

}