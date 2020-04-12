package org.javagram.api.help.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.json.base.TLDataJSON;
import org.javagram.api._primitives.TLBool;

/**
 * Accept the new terms of service
 * help.acceptTermsOfService#ee72f79a id:DataJSON = Bool;
 */
public class TLRequestHelpAcceptTermsOfService extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xee72f79a;
    
    /**
     * ID of terms of service
     */
    private TLDataJSON id;

    public TLRequestHelpAcceptTermsOfService() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLDataJSON getId() {
        return id;
    }

    public void setId(TLDataJSON id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.acceptTermsOfService#ee72f79a";
    }

}