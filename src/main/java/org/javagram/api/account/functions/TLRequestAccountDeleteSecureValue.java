package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.secure.base.valuetype.TLAbsSecureValueType;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLVector;

/**
 * Delete stored @see <a href="https://core.telegram.org/passport">Telegram Passport</a> documents, @see <a href="https://core.telegram.org/passport/encryption#encryption">for more info see the passport docs »</a>
 * account.deleteSecureValue#b880bc4b types:Vector&lt;SecureValueType&gt; = Bool;
 */
public class TLRequestAccountDeleteSecureValue extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb880bc4b;
    
    /**
     * Document types to delete
     */
    private TLVector<TLAbsSecureValueType> types;

    public TLRequestAccountDeleteSecureValue() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsSecureValueType> getTypes() {
        return types;
    }

    public void setTypes(TLVector<TLAbsSecureValueType> types) {
        this.types = types;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.types, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.types = StreamingUtils.readTLVector(stream, context, TLAbsSecureValueType.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsAccountPassword, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.deleteSecureValue#b880bc4b";
    }

}