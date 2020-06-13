package org.javagram.mtproto.tl.pq;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

public class PQInnerDataTemp extends PQInnerData {

    public static final int CLASS_ID = 0x3c6a84d4;

    private int expiresIn;

    public PQInnerDataTemp() { 
        super();
    }

    public PQInnerDataTemp(byte[] pq, byte[] p, byte[] q, byte[] nonce, byte[] serverNonce, byte[] newNonce, int expiresIn) {
        super(pq, p, q, nonce, serverNonce, newNonce);
        this.expiresIn = expiresIn;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeInt(this.expiresIn, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.expiresIn = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "p_q_inner_data_temp#3c6a84d4";
    }

}