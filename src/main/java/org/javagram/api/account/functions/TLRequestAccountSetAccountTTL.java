package org.javagram.api.account.functions;

import org.javagram.api.account.base.TLAccountDaysTTL;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Set account self-destruction period
 * account.setAccountTTL#2442485e ttl:AccountDaysTTL = Bool;
 */
public class TLRequestAccountSetAccountTTL extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2442485e;

    /**
     * Time to live in days
     */
    private TLAccountDaysTTL ttl;

    public TLRequestAccountSetAccountTTL() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAccountDaysTTL getTtl() {
        return this.ttl;
    }

    public void setTtl(TLAccountDaysTTL ttl) {
        this.ttl = ttl;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.ttl, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.ttl = StreamingUtils.readTLObject(stream, context, TLAccountDaysTTL.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.setAccountTTL#2442485e";
    }

}