package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.javagram.utils.StreamingUtils;

public class MTGetFutureSalts extends TLObject {

    public static final int CLASS_ID = 0xb921bd04;

    private int num;

    public MTGetFutureSalts(int num) {
        this.num = num;
    }

    public MTGetFutureSalts() { }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.num, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.num = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "get_future_salts#b921bd04";
    }

}