package org.telegram.api.secure.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;

/**
 * secureData
 * Secure @see <a href="https://core.telegram.org/passport">passport</a> data, for more info @see <a href="https://core.telegram.org/passport/encryption#securedata">see the passport docs »</a>
 * secureData#8aeabec3 data:bytes data_hash:bytes secret:bytes = SecureData;
 */
public class TLSecureData extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8aeabec3;

    /**
     * Data
     */
    private TLBytes data;
    
    /**
     * Data hash
     */
    private TLBytes dataHash;
    
    /**
     * Secret
     */
    private TLBytes secret;
    
    public TLSecureData() {
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

    public TLBytes getDataHash() {
        return dataHash;
    }

    public void setDataHash(TLBytes dataHash) {
        this.dataHash = dataHash;
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
        StreamingUtils.writeTLBytes(this.dataHash, stream);
        StreamingUtils.writeTLBytes(this.secret, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.data = StreamingUtils.readTLBytes(stream, context);
        this.dataHash = StreamingUtils.readTLBytes(stream, context);
        this.secret = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "secureData#8aeabec3";
    }

}
