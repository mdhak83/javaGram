package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.secure.base.TLInputSecureValue;
import org.telegram.api.secure.base.TLSecureValue;

/**
 * Securely save @see <a href="https://core.telegram.org/passport">Telegram Passport</a> documents, @see <a href="https://core.telegram.org/passport/encryption#encryption">for more info see the passport docs »</a>
 * account.saveSecureValue#899fe31d value:InputSecureValue secure_secret_id:long = SecureValue;
 */
public class TLRequestAccountSaveSecureValue extends TLMethod<TLSecureValue> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x899fe31d;
    
    /**
     * Secure value, @see <a href="https://core.telegram.org/passport/encryption#encryption">for more info see the passport docs »</a>
     */
    private TLInputSecureValue value;
    
    /**
     * Passport secret hash, @see <a href="https://core.telegram.org/passport/encryption#encryption">for more info see the passport docs »</a>
     */
    private long secureSecretId;

    public TLRequestAccountSaveSecureValue() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLInputSecureValue getValue() {
        return value;
    }

    public void setValue(TLInputSecureValue value) {
        this.value = value;
    }

    public long getSecureSecretId() {
        return secureSecretId;
    }

    public void setSecureSecretId(long secureSecretId) {
        this.secureSecretId = secureSecretId;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.value, stream);
        StreamingUtils.writeLong(this.secureSecretId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.value = StreamingUtils.readTLObject(stream, context, TLInputSecureValue.class);
        this.secureSecretId = StreamingUtils.readLong(stream);
    }

    @Override
    public TLSecureValue deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLSecureValue) {
            return (TLSecureValue) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsAccountPassword, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.saveSecureValue#899fe31d";
    }

}