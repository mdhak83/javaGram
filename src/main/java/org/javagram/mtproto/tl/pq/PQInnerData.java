package org.javagram.mtproto.tl.pq;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

public class PQInnerData extends TLObject {

    public static final int CLASS_ID = 0x83c95aec;

    protected byte[] pq;
    protected byte[] p;
    protected byte[] q;
    protected byte[] nonce;
    protected byte[] serverNonce;
    protected byte[] newNonce;

    public PQInnerData() { }

    public PQInnerData(byte[] pq, byte[] p, byte[] q, byte[] nonce, byte[] serverNonce, byte[] newNonce) {
        this.pq = pq;
        this.p = p;
        this.q = q;
        this.nonce = nonce;
        this.serverNonce = serverNonce;
        this.newNonce = newNonce;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public byte[] getPq() {
        return this.pq;
    }

    public byte[] getP() {
        return this.p;
    }

    public byte[] getQ() {
        return this.q;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public byte[] getServerNonce() {
        return this.serverNonce;
    }

    public byte[] getNewNonce() {
        return this.newNonce;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.pq, stream);
        StreamingUtils.writeTLBytes(this.p, stream);
        StreamingUtils.writeTLBytes(this.q, stream);
        StreamingUtils.writeByteArray(this.nonce, stream);
        StreamingUtils.writeByteArray(this.serverNonce, stream);
        StreamingUtils.writeByteArray(this.newNonce, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.pq = StreamingUtils.readTLBytes(stream);
        this.p = StreamingUtils.readTLBytes(stream);
        this.q = StreamingUtils.readTLBytes(stream);
        this.nonce = StreamingUtils.readBytes(16, stream);
        this.serverNonce = StreamingUtils.readBytes(16, stream);
        this.newNonce = StreamingUtils.readBytes(32, stream);
    }

    @Override
    public String toString() {
        return "p_q_inner_data#83c95aec";
    }

}