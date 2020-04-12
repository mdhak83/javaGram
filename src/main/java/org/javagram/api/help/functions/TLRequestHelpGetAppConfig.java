package org.javagram.api.help.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.javagram.api.json.base.TLAbsJSONValue;

/**
 * Get app-specific configuration
 * help.getAppConfig#98914110 = JSONValue;
 */
public class TLRequestHelpGetAppConfig extends TLMethod<TLAbsJSONValue> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x98914110;
    
    public TLRequestHelpGetAppConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsJSONValue deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsJSONValue) {
            return (TLAbsJSONValue) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getAppConfig#98914110";
    }

}