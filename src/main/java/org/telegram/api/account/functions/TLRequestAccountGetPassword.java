package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.account.base.TLAccountPassword;

/**
 * Obtain configuration for two-factor authorization with password
 * account.getPassword#548a30f5 = account.Password;
 */
public class TLRequestAccountGetPassword extends TLMethod<TLAccountPassword> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x548a30f5;

    public TLRequestAccountGetPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAccountPassword deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountPassword) {
            return (TLAccountPassword) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsAccountPassword, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getPassword#548a30f5";
    }
    
}