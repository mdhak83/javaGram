package org.javagram.mtproto.tl.pq;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.DeserializeException;
import static org.javagram.utils.StreamingUtils.readBytes;
import static org.javagram.utils.StreamingUtils.readLong;
import static org.javagram.utils.StreamingUtils.readTLBytes;
import static org.javagram.utils.StreamingUtils.writeByteArray;
import static org.javagram.utils.StreamingUtils.writeLong;
import static org.javagram.utils.StreamingUtils.writeTLBytes;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 6:26
 */
public class ReqDhParams extends TLMethod<ServerDhParams> {

    public static final int CLASS_ID = 0xd712e4be;
    protected byte[] nonce;
    protected byte[] serverNonce;
    protected byte[] p;
    protected byte[] q;
    protected long fingerPrint;
    protected byte[] encryptedData;
    public ReqDhParams(byte[] nonce, byte[] serverNonce, byte[] p, byte[] q, long fingerPrint, byte[] encryptedData) {
        this.nonce = nonce;
        this.serverNonce = serverNonce;
        this.p = p;
        this.q = q;
        this.fingerPrint = fingerPrint;
        this.encryptedData = encryptedData;
    }

    public ReqDhParams() {

    }

    @Override
    public ServerDhParams deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject response = context.deserializeMessage(stream);

        if (response == null) {
            throw new DeserializeException("Unable to deserialize response");
        }
        if (!(response instanceof ServerDhParams)) {
            throw new DeserializeException("Response has incorrect type");
        }

        return (ServerDhParams) response;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public byte[] getNonce() {
        return this.nonce;
    }

    public byte[] getServerNonce() {
        return this.serverNonce;
    }

    public byte[] getP() {
        return this.p;
    }

    public byte[] getQ() {
        return this.q;
    }

    public long getFingerPrint() {
        return this.fingerPrint;
    }

    public byte[] getEncryptedData() {
        return this.encryptedData;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeByteArray(this.nonce, stream);
        writeByteArray(this.serverNonce, stream);
        writeTLBytes(this.p, stream);
        writeTLBytes(this.q, stream);
        writeLong(this.fingerPrint, stream);
        writeTLBytes(this.encryptedData, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.nonce = readBytes(16, stream);
        this.serverNonce = readBytes(16, stream);
        this.p = readTLBytes(stream);
        this.q = readTLBytes(stream);
        this.fingerPrint = readLong(stream);
        this.encryptedData = readTLBytes(stream);
    }

    @Override
    public String toString() {
        return "req_DH_params#d712e4be";
    }
}
