package org.telegram.api.help.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.help.base.TLAbsHelpDeepLinkInfo;

/**
 * Get info about a <code>t.me</code> link
 * help.getDeepLinkInfo#3fedc75f path:string = help.DeepLinkInfo;
 */
public class TLRequestHelpGetDeepLinkInfo extends TLMethod<TLAbsHelpDeepLinkInfo> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3fedc75f;
    
    /**
     * Path in <code>t.me/path</code>
     */
    private String path;

    public TLRequestHelpGetDeepLinkInfo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.path, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.path = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsHelpDeepLinkInfo deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsHelpDeepLinkInfo) {
            return (TLAbsHelpDeepLinkInfo) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getDeepLinkInfo#3fedc75f";
    }

}