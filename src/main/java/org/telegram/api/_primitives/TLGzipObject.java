package org.telegram.api._primitives;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * Basic class of gzipped object
 * gzip_packed#3072cfa1 packed_data:bytes = Object;
 */
public class TLGzipObject extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3072cfa1;
    
    private byte[] packedData;

    public TLGzipObject() { }

    public TLGzipObject(byte[] packedData) {
        this.packedData = packedData;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public byte[] getPackedData() {
        return this.packedData;
    }

    public void setPackedData(byte[] packedData) {
        this.packedData = packedData;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.packedData, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.packedData = StreamingUtils.readTLBytes(stream);
    }

    @Override
    public String toString() {
        return "gzip_packed#3072cfa1";
    }

}