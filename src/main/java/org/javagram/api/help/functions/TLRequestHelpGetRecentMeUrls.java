package org.javagram.api.help.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.javagram.api.recentmeurl.base.TLRecentMeUrls;

/**
 * Get recently used <code>t.me</code> links
 * help.getRecentMeUrls#3dc0f114 referer:string = help.RecentMeUrls;
 */
public class TLRequestHelpGetRecentMeUrls extends TLMethod<TLRecentMeUrls> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3dc0f114;
    
    /**
     * Referer
     */
    private String referer;

    public TLRequestHelpGetRecentMeUrls() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    @Override
    public TLRecentMeUrls deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLRecentMeUrls) {
            return (TLRecentMeUrls) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getRecentMeUrls#3dc0f114";
    }

}