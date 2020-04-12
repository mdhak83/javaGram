package org.javagram.api.help.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.api._primitives.TLVector;

/**
 * Promotion channel associated to a certain MTProxy
 * help.proxyDataPromo#2bf7ee23 expires:int peer:Peer chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = help.ProxyData;
 */
public class TLHelpProxyDataPromo extends TLAbsHelpProxyData {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2bf7ee23;

    /**
     * Expiration date of proxy info, will have to be refetched in <code>expires</code> seconds
     */
    private int expires;
    
    /**
     * The promoted channel
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

    public TLHelpProxyDataPromo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
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
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.expires, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.expires = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "help.proxyDataPromo#2bf7ee23";
    }

}