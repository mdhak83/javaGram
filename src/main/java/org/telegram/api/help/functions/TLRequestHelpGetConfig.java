package org.telegram.api.help.functions;

import org.telegram.api.TLConfig;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns current configuration, icluding data center configuration.
 * help.getConfig#c4f9186b = Config;
 */
public class TLRequestHelpGetConfig extends TLMethod<TLConfig> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4f9186b;

    public TLRequestHelpGetConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLConfig deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLConfig) {
            return (TLConfig) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.TLConfig, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getConfig#c4f9186b";
    }

}