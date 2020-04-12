package org.javagram.api.help.functions;

import org.javagram.api.cdn.base.TLCdnConfig;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Get configuration for @see <a href="https://core.telegram.org/cdn">CDN</a> file downloads.
 * help.getCdnConfig#52029342 = CdnConfig;
 */
public class TLRequestHelpGetCdnConfig extends TLMethod<TLCdnConfig> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x52029342;

    public TLRequestHelpGetCdnConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLCdnConfig deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLCdnConfig) {
            return (TLCdnConfig) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLCdnConfig.class.getCanonicalName()  + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getCdnConfig#52029342";
    }

}