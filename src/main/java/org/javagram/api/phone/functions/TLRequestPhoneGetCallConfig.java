package org.javagram.api.phone.functions;

import org.javagram.api.json.base.TLDataJSON;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPhoneGetCallConfig extends TLMethod<TLDataJSON> {
    public static final int CLASS_ID = 0x55451fa9;

    public TLRequestPhoneGetCallConfig() {
        super();
    }

    @Override
    public TLDataJSON deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLDataJSON) {
            return (TLDataJSON) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLDataJSON.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "phone.getCallConfig#55451fa9";
    }

}
