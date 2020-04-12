package org.javagram.api.messages.base;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.chat.base.TLAbsChatFull;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages chat full.
 */
public class TLMessagesChatFull extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe5d7d19c;

    private TLAbsChatFull fullChat;
    private TLVector<TLAbsChat> chats = new TLVector<>();
    private TLVector<TLAbsUser> users = new TLVector<>();

    public TLMessagesChatFull() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets full chat.
     *
     * @return the full chat
     */
    public TLAbsChatFull getFullChat() {
        return this.fullChat;
    }

    /**
     * Sets full chat.
     *
     * @param value the value
     */
    public void setFullChat(TLAbsChatFull value) {
        this.fullChat = value;
    }

    /**
     * Gets chats.
     *
     * @return the chats
     */
    public TLVector<TLAbsChat> getChats() {
        return this.chats;
    }

    /**
     * Sets chats.
     *
     * @param value the value
     */
    public void setChats(TLVector<TLAbsChat> value) {
        this.chats = value;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param value the value
     */
    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.fullChat, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.fullChat = StreamingUtils.readTLObject(stream, context, TLAbsChatFull.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.chatFull#e5d7d19c";
    }

}