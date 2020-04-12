package org.telegram.api.contacts.base.toppeers;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.toppeer.base.TLTopPeerCategoryPeers;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Top peers
 * contacts.topPeers#70b772a8 categories:Vector&lt;TopPeerCategoryPeers&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = contacts.TopPeers;
 */
public class TLContactsTopPeers extends TLAbsContactsTopPeers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x70b772a8;

    /**
     * Top peers by top peer category
     */
    private TLVector<TLTopPeerCategoryPeers> categories;
    
    /**
     * Chats
     */
    private TLVector<TLAbsChat> chats;
    
    /**
     * Users
     */
    private TLVector<TLAbsUser> users;

    public TLContactsTopPeers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLTopPeerCategoryPeers> getCategories() {
        return categories;
    }

    public void setCategories(TLVector<TLTopPeerCategoryPeers> categories) {
        this.categories = categories;
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
        StreamingUtils.writeTLVector(this.categories, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.categories = StreamingUtils.readTLVector(stream, context, TLTopPeerCategoryPeers.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "contacts.topPeers#70b772a8";
    }

}