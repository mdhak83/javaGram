package org.javagram.mtproto.tl.pq;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

public class PQInnerDataDC extends PQInnerData {

    public static final int CLASS_ID = 0xa9f55f95;

    private int dc;

    public PQInnerDataDC() { 
        super();
    }

    public PQInnerDataDC(byte[] pq, byte[] p, byte[] q, byte[] nonce, byte[] serverNonce, byte[] newNonce, int dc) {
        super(pq, p, q, nonce, serverNonce, newNonce);
        this.dc = dc;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDc() {
        return dc;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeInt(this.dc, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.dc = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "p_q_inner_data_dc#a9f55f95";
    }

}