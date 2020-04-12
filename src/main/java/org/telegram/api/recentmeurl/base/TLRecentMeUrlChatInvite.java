package org.telegram.api.recentmeurl.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.invite.TLAbsChatInvite;

/**
 * Recent t.me invite link to a chat
 * recentMeUrlChatInvite#eb49081d url:string chat_invite:ChatInvite = RecentMeUrl;
 */
public class TLRecentMeUrlChatInvite extends TLAbsRecentMeUrl {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeb49081d;

    /**
     * URL
     */
    private String url;
    
    /**
     * Chat invitation
     */
    private TLAbsChatInvite chatInvite;

    public TLRecentMeUrlChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public TLAbsChatInvite getChatInvite() {
        return chatInvite;
    }

    public void setChatInvite(TLAbsChatInvite chatInvite) {
        this.chatInvite = chatInvite;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLObject(this.chatInvite, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.chatInvite = StreamingUtils.readTLObject(stream, context, TLAbsChatInvite.class);
    }

    @Override
    public String toString() {
        return "recentMeUrlChatInvite#eb49081d";
    }

}