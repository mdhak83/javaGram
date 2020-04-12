package org.telegram.api.file.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input file big.
 */
public class TLInputFileBig extends TLAbsInputFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfa4f0bb5;

    public TLInputFileBig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.parts, stream);
        StreamingUtils.writeTLString(this.name, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.parts = StreamingUtils.readInt(stream);
        this.name = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputFileBig#fa4f0bb5";
    }

}