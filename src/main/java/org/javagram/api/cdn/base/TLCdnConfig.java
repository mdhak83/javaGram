package org.javagram.api.cdn.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLCdnConfig extends TLObject {
    public static final int CLASS_ID = 0x5725e40a;

    private TLVector<TLCdnPublicKey> publicKeys;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLCdnPublicKey> getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(TLVector<TLCdnPublicKey> publicKeys) {
        this.publicKeys = publicKeys;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(publicKeys, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        publicKeys = StreamingUtils.readTLVector(stream, context, TLCdnPublicKey.class);
    }

    @Override
    public String toString() {
        return "cdnConfig#5725e40a";
    }

}
