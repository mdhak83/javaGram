package org.javagram.api.auth.base.sentcode;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @since 16/MAR/2016
 */
public class TLSentCodeTypeCall extends TLAbsSentCodeType {
    public static final int CLASS_ID = 0x5353e5a7;

    private int length;

    public TLSentCodeTypeCall() {
        super();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(length, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        length = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "auth.sentCodeTypeCall#5353e5a7";
    }

}
