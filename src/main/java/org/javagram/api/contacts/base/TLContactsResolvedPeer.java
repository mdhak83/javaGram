package org.javagram.api.contacts.base;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Resolved peer
 * contacts.resolvedPeer#7f077ad9 peer:Peer chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = contacts.ResolvedPeer;
 */
public class TLContactsResolvedPeer extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f077ad9;

    /**
     * The peer
     */
    private TLAbsPeer peer;
    
    /**
     * Chats
     */
    private TLVector<TLAbsChat> chats;
    
    /**
     * Users
     */
    private TLVector<TLAbsUser> users;

    public TLContactsResolvedPeer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
    }

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

    public void setChats(TLVector<TLAbsChat> chats) {
        this.chats = chats;
    }

    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "contacts.resolvedPeer#7f077ad9";
    }

}