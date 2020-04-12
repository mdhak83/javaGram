package org.telegram.api.photos.functions;

import org.telegram.api.file.base.input.TLAbsInputFile;
import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.api.photo.base.TLPhoto;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request photos upload profile photo.
 */
public class TLRequestPhotosUploadProfilePhoto extends TLMethod<TLPhoto> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4f32c098;

    private TLAbsInputFile file;

    public TLRequestPhotosUploadProfilePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLPhoto deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLPhoto)
            return (TLPhoto) res;
        throw new IOException("Incorrect response type. Expected " + TLAbsPhoto.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public TLAbsInputFile getFile() {
        return this.file;
    }

    /**
     * Sets file.
     *
     * @param value the value
     */
    public void setFile(TLAbsInputFile value) {
        this.file = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.file, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
    }

    @Override
    public String toString() {
        return "photos.uploadProfilePhoto#4f32c098";
    }

}