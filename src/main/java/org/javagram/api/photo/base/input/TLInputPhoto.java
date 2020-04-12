package org.javagram.api.photo.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * inputPhoto
 * Defines a photo for further interaction.
 * inputPhoto#3bb3b94a id:long access_hash:long file_reference:bytes = InputPhoto;
 */
public class TLInputPhoto extends TLAbsInputPhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3bb3b94a;

    private long id;
    private long accessHash;
    private TLBytes fileReference;

    public TLInputPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public TLBytes getFileReference() {
        return fileReference;
    }

    public void setFileReference(TLBytes fileReference) {
        this.fileReference = fileReference;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLBytes(this.fileReference, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.fileReference = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "inputPhoto#3bb3b94a";
    }

}