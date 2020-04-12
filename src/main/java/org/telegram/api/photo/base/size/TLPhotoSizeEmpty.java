package org.telegram.api.photo.base.size;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Empty constructor. Image with this thumbnail is unavailable.
 * photoSizeEmpty#e17e23c type:string = PhotoSize;
 */
public class TLPhotoSizeEmpty extends TLAbsPhotoSize {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe17e23c;
    
    public TLPhotoSizeEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.type, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.type = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "photoSizeEmpty#e17e23c";
    }

}