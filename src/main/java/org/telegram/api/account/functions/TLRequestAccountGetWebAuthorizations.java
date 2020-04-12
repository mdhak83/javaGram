package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.account.base.TLAccountWebAuthorizations;

/**
 * Get web @see <a href="https://core.telegram.org/widgets/login">login widget</a> authorizations
 * account.getWebAuthorizations#182e6d6f = account.WebAuthorizations;
 */
public class TLRequestAccountGetWebAuthorizations extends TLMethod<TLAccountWebAuthorizations> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x182e6d6f;

    public TLRequestAccountGetWebAuthorizations() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAccountWebAuthorizations deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountWebAuthorizations) {
            return (TLAccountWebAuthorizations) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.account.TLAuthorizations, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getWebAuthorizations#182e6d6f";
    }

}