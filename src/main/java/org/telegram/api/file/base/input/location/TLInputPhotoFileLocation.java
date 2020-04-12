package org.telegram.api.file.base.input.location;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;

/**
 * Use this object to download a photo with @see <a href="https://core.telegram.org/method/upload.getFile">upload.getFile</a> method
 * inputPhotoFileLocation#40181ffe id:long access_hash:long file_reference:bytes thumb_size:string = InputFileLocation;
 */
public class TLInputPhotoFileLocation extends TLAbsInputFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x40181ffe;

    /**
     * Photo ID, obtained from the @see <a href="https://core.telegram.org/constructor/photo">photo</a> object
     */
    private long id;

    /**
     * Photo's access hash, obtained from the @see <a href="https://core.telegram.org/constructor/photo">photo</a> object
     */
    private long accessHash;

    /**
     * @see <a href="https://core.telegram.org/api/file_reference">File reference</a>
     */
    private TLBytes fileReference;

    /**
     * The @see <a href="https://core.telegram.org/type/PhotoSize">PhotoSize</a> to download: must be set to the type field of the desired PhotoSize object of the @see <a href="https://core.telegram.org/constructor/photo">photo</a>
     */
    private String thumbSize;

    public TLInputPhotoFileLocation() {
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

    public String getThumbSize() {
        return thumbSize;
    }

    public void setThumbSize(String thumbSize) {
        this.thumbSize = thumbSize;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLBytes(this.fileReference, stream);
        StreamingUtils.writeTLString(this.thumbSize, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.fileReference = StreamingUtils.readTLBytes(stream, context);
        this.thumbSize = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputPhotoFileLocation#40181ffe";
    }

}