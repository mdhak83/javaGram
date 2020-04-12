package org.telegram.api.auth.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Terminates all user's authorized sessions except for the current one.
 * After calling this method it is necessary to reregister the current device using the method @see <a href="https://core.telegram.org/method/account.registerDevice">account.registerDevice</a>
 * auth.resetAuthorizations#9fab0d1a = Bool;
 */
public class TLRequestAuthResetAuthorizations extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9fab0d1a;

    public TLRequestAuthResetAuthorizations() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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
        return "auth.resetAuthorizations#9fab0d1a";
    }

}