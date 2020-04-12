package org.telegram.api.secure.base;

import org.telegram.api.password.base.TLAbsSecurePasswordKdfAlgo;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLObject;

/**
 * secureSecretSettings
 * Secure settings
 * secureSecretSettings#1527bcac secure_algo:SecurePasswordKdfAlgo secure_secret:bytes secure_secret_id:long = SecureSecretSettings;
 */
public class TLSecureSecretSettings extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1527bcac;

    /**
     * Secure KDF algo
     */
    private TLAbsSecurePasswordKdfAlgo secureAlgo;

    /**
     * Secure secret
     */
    private TLBytes secureSecret;

    /**
     * Secret ID
     */
    private long secureSecretId;

    public TLSecureSecretSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsSecurePasswordKdfAlgo getSecureAlgo() {
        return secureAlgo;
    }

    public void setSecureAlgo(TLAbsSecurePasswordKdfAlgo secureAlgo) {
        this.secureAlgo = secureAlgo;
    }

    public TLBytes getSecureSecret() {
        return secureSecret;
    }

    public void setSecureSecret(TLBytes secureSecret) {
        this.secureSecret = secureSecret;
    }

    public long getSecureSecretId() {
        return secureSecretId;
    }

    public void setSecureSecretId(long secureSecretId) {
        this.secureSecretId = secureSecretId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.secureAlgo, stream);
        StreamingUtils.writeTLBytes(this.secureSecret, stream);
        StreamingUtils.writeLong(this.secureSecretId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.secureAlgo = StreamingUtils.readTLObject(stream, context, TLAbsSecurePasswordKdfAlgo.class);
        this.secureSecret = StreamingUtils.readTLBytes(stream, context);
        this.secureSecretId = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "secureSecretSettings#1527bcac";
    }

}