package org.javagram.mtproto.tl.pq;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.DeserializeException;
import org.javagram.utils.StreamingUtils;

public class ReqPQMulti extends TLMethod<ResPQ> {

    public static final int CLASS_ID = 0xbe7e8ef1;

    protected byte[] nonce;

    public ReqPQMulti() { }

    public ReqPQMulti(byte[] nonce) {
        if (nonce == null || nonce.length != 16) {
            throw new IllegalArgumentException("nonce might be not null and 16 bytes length");
        }
        this.nonce = nonce;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public void setNonce(byte[] nonce) {
        if (nonce == null || nonce.length != 16) {
            throw new IllegalArgumentException("nonce might be not null and 16 bytes length");
        }
        this.nonce = nonce;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeByteArray(this.nonce, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.nonce = StreamingUtils.readBytes(16, stream);
    }

    @Override
    public ResPQ deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject response = context.deserializeMessage(stream);
        if (response == null) {
            throw new DeserializeException("Unable to deserialize response");
        } else  if (!(response instanceof ResPQ)) {
            throw new DeserializeException("Response has incorrect type");
        } else {
            return (ResPQ) response;
        }
    }

    @Override
    public String toString() {
        return "req_pq_multi#be7e8ef1";
    }

}