package org.telegram.api.recentmeurl.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Unknown t.me url
 * recentMeUrlUnknown#46e1d13d url:string = RecentMeUrl;
 */
public class TLRecentMeUrlUnknown extends TLAbsRecentMeUrl {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x46e1d13d;

    /**
     * URL
     */
    private String url;

    public TLRecentMeUrlUnknown() {
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
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "recentMeUrlUnknown#46e1d13d";
    }

}