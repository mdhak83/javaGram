package org.telegram.api.secure.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api.secure.base.valuetype.TLAbsSecureValueType;

/**
 * secureValueHash
 * Secure value hash
 * secureValueHash#ed1ecdb0 type:SecureValueType hash:bytes = SecureValueHash;
 */
public class TLSecureValueHash extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xed1ecdb0;

    /**
     * Secure value type
     */
    private TLAbsSecureValueType type;
    
    /**
     * Hash
     */
    private TLBytes hash;

    public TLSecureValueHash() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public TLAbsSecureValueType getType() {
        return type;
    }

    public void setType(TLAbsSecureValueType type) {
        this.type = type;
    }

    public TLBytes getHash() {
        return hash;
    }

    public void setHash(TLBytes hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.type, stream);
        StreamingUtils.writeTLBytes(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLObject(stream, context, TLAbsSecureValueType.class);
        this.hash = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "secureValueHash#ed1ecdb0";
    }

}