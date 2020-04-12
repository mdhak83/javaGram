package org.telegram.api.secure.base.credentials;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;

/**
 * secureCredentialsEncrypted
 * Encrypted credentials required to decrypt telegram @see <a href="https://core.telegram.org/passport">passport</a> data.
 * secureCredentialsEncrypted#33f0ea47 data:bytes hash:bytes secret:bytes = SecureCredentialsEncrypted;
 */
public class TLSecureCredentialsEncrypted extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x33f0ea47;

    /**
     * Encrypted JSON-serialized data with unique user's payload, data hashes and secrets required for EncryptedPassportElement decryption and authentication, as described in @see <a href="https://core.telegram.org/passport#decrypting-data">decrypting data »</a>
     */
    private TLBytes data;
    
    /**
     * Data hash for data authentication as described in @see <a href="https://core.telegram.org/passport#decrypting-data">decrypting data »</a>
     */
    private TLBytes hash;
    
    /**
     * Secret, encrypted with the bot's public RSA key, required for data decryption as described in @see <a href="https://core.telegram.org/passport#decrypting-data">decrypting data »</a>
     */
    private TLBytes secret;
    
    public TLSecureCredentialsEncrypted() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getData() {
        return data;
    }

    public void setData(TLBytes data) {
        this.data = data;
    }

    public TLBytes getHash() {
        return hash;
    }

    public void setHash(TLBytes hash) {
        this.hash = hash;
    }

    public TLBytes getSecret() {
        return secret;
    }

    public void setSecret(TLBytes secret) {
        this.secret = secret;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.data, stream);
        StreamingUtils.writeTLBytes(this.hash, stream);
        StreamingUtils.writeTLBytes(this.secret, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.data = StreamingUtils.readTLBytes(stream, context);
        this.hash = StreamingUtils.readTLBytes(stream, context);
        this.secret = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "secureCredentialsEncrypted#33f0ea47";
    }

}