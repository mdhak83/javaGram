package org.telegram.api.help.functions;

import org.telegram.api.TLNearestDc;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns info on data centre nearest to the user.
 * help.getNearestDc#1fb33026 = NearestDc;
 */
public class TLRequestHelpGetNearestDc extends TLMethod<TLNearestDc> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1fb33026;

    public TLRequestHelpGetNearestDc() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLNearestDc deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLNearestDc) {
            return (TLNearestDc) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.TLNearestDc, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getNearestDc#1fb33026";
    }

}