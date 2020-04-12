package org.javagram.api.account.functions;

import org.javagram.api.account.base.TLAccountDaysTTL;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Get days to live of account
 * account.getAccountTTL#8fc711d = AccountDaysTTL;
 */
public class TLRequestAccountGetAccountTTL extends TLMethod<TLAccountDaysTTL> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8fc711d;

    public TLRequestAccountGetAccountTTL() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAccountDaysTTL deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountDaysTTL) {
            return (TLAccountDaysTTL) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getAccountTTL#8fc711d";
    }
    
}