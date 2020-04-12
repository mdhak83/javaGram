package org.telegram.api.account.functions;

import org.telegram.api.account.base.TLAccountAuthorizations;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Get logged-in sessions
 * account.getAuthorizations#e320c158 = account.Authorizations;
 */
public class TLRequestAccountGetAuthorizations extends TLMethod<TLAccountAuthorizations> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe320c158;

    public TLRequestAccountGetAuthorizations() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAccountAuthorizations deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountAuthorizations) {
            return (TLAccountAuthorizations) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.account.TLAuthorizations, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getAuthorizations#e320c158";
    }

}