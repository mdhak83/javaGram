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
 * Get saved @see <a href="https://core.telegram.org/passport">Telegram Passport</a> documents, @see <a href="https://core.telegram.org/passport/encryption#encryption">for more info see the passport docs »</a>
 * account.getSecureValue#73665bc2 types:Vector&lt;SecureValueType&gt; = Vector&lt;SecureValue&gt;;
 */
public class TLRequestAccountGetSecureValue extends TLMethod<TLVector<TLSecureValue>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x73665bc2;

    public TLRequestAccountGetSecureValue() {
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
        return "account.getSecureValue#73665bc2";
    }
    
}