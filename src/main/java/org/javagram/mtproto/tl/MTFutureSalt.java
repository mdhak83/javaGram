package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

public class MTFutureSalt extends TLObject {

    public static final int CLASS_ID = 0x0949d9dc;

    private int validSince;
    private int validUntil;
    private long salt;

    public MTFutureSalt(int validSince, int validUntil, long salt) {
        this.validSince = validSince;
        this.validUntil = validUntil;
        this.salt = salt;
    }

    public MTFutureSalt() {

    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getValidSince() {
        return this.validSince;
    }

    public int getValidUntil() {
        return this.validUntil;
    }

    public long getSalt() {
        return this.salt;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.validSince, stream);
        StreamingUtils.writeInt(this.validUntil, stream);
        StreamingUtils.writeLong(this.salt, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.validSince = StreamingUtils.readInt(stream);
        this.validUntil = StreamingUtils.readInt(stream);
        this.salt = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "future_salt#0949d9dc";
    }
}
