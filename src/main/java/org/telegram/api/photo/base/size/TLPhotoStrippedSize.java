package org.telegram.api.photo.base.size;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;

/**
 * Just the image's content
 * photoStrippedSize#e0b0bc2e type:string bytes:bytes = PhotoSize;
 */
public class TLPhotoStrippedSize extends TLAbsPhotoSize {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe0b0bc2e;

    /**
     * Binary data, file content
     */
    private TLBytes bytes;

    public TLPhotoStrippedSize() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getBytes() {
        return this.bytes;
    }

    public void setBytes(TLBytes bytes) {
        this.bytes = bytes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.type, stream);
        StreamingUtils.writeTLBytes(this.bytes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLString(stream);
        this.bytes = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "photoStrippedSize#e0b0bc2e";
    }

}