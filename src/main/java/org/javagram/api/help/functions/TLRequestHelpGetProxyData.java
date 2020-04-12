package org.javagram.api.help.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.javagram.api.help.base.TLAbsHelpProxyData;

/**
 * Get promotion info of the currently-used MTProxy
 * help.getProxyData#3d7758e1 = help.ProxyData;
 */
public class TLRequestHelpGetProxyData extends TLMethod<TLAbsHelpProxyData> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3d7758e1;
    
    public TLRequestHelpGetProxyData() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsHelpProxyData deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsHelpProxyData) {
            return (TLAbsHelpProxyData) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getProxyData#3d7758e1";
    }

}