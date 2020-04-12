package org.telegram.api.updates.base.difference;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL difference empty.
 */
public class TLDifferenceEmpty extends TLAbsDifference {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5d75a138;

    public TLDifferenceEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.seq, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.date = StreamingUtils.readInt(stream);
        this.seq = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updates.differenceEmpty#5d75a138";
    }

}