package org.telegram.api.recentmeurl.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Recent t.me link to a chat
 * recentMeUrlChat#a01b22f9 url:string chat_id:int = RecentMeUrl;
 */
public class TLRecentMeUrlChat extends TLAbsRecentMeUrl {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa01b22f9;

    /**
     * URL
     */
    private String url;
    
    /**
     * Chat ID
     */
    private int chatId;

    public TLRecentMeUrlChat() {
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
    
    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeInt(this.chatId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.chatId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "recentMeUrlChat#a01b22f9";
    }

}