package org.javagram.api.recentmeurl.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;

/**
 * Recent t.me URLs
 * help.recentMeUrls#e0310d7 urls:Vector&lt;RecentMeUrl&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = help.RecentMeUrls;
 */
public class TLRecentMeUrls extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe0310d7;

    /**
     * URLs
     */
    private TLVector<TLAbsRecentMeUrl> urls;
    
    /**
     * Chats
     */
    private TLVector<TLAbsChat> chats;

    /**
     * Users
     */
    private TLVector<TLAbsUser> users;

    public TLRecentMeUrls() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsRecentMeUrl> getUrls() {
        return urls;
    }

    public void setUrls(TLVector<TLAbsRecentMeUrl> urls) {
        this.urls = urls;
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
        StreamingUtils.writeTLVector(this.urls, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.urls = StreamingUtils.readTLVector(stream, context, TLAbsRecentMeUrl.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "help.recentMeUrls#e0310d7";
    }

}