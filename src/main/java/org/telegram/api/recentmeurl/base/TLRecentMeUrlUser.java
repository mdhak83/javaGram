package org.telegram.api.recentmeurl.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Recent t.me link to a user
 * recentMeUrlUser#8dbc3336 url:string user_id:int = RecentMeUrl;
 */
public class TLRecentMeUrlUser extends TLAbsRecentMeUrl {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8dbc3336;

    /**
     * URL
     */
    private String url;
    
    /**
     * User ID
     */
    private int userId;

    public TLRecentMeUrlUser() {
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
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeInt(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.userId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "recentMeUrlUser#8dbc3336";
    }

}