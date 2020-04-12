package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.javagram.api.secure.base.TLSecureValue;
import org.javagram.api._primitives.TLVector;

/**
 * Get all saved @see <a href="https://core.telegram.org/passport">Telegram Passport</a> documents, @see <a href="https://core.telegram.org/passport/encryption#encryption">for more info see the passport docs »</a>
 * account.getAllSecureValues#b288bc7d = Vector&lt;SecureValue&gt;;
 */
public class TLRequestAccountGetAllSecureValues extends TLMethod<TLVector<TLSecureValue>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb288bc7d;

    public TLRequestAccountGetAllSecureValues() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLVector<TLSecureValue> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLVector) {
            return (TLVector<TLSecureValue>) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsAccountPassword, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getAllSecureValues#b288bc7d";
    }
    
}