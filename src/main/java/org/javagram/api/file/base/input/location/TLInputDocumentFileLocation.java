package org.javagram.api.file.base.input.location;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * Document location (video, voice, audio, basically every type except photo)
 * inputDocumentFileLocation#bad07584 id:long access_hash:long file_reference:bytes thumb_size:string = InputFileLocation;
 */
public class TLInputDocumentFileLocation extends TLAbsInputFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbad07584;

    /**
     * Document ID
     */
    private long id;
    
    /**
     * access_hash parameter from the @see <a href="https://core.telegram.org/constructor/document">document</a> constructor
     */
    private long accessHash;
    
    /**
     * @see <a href="https://core.telegram.org/api/file_reference">File reference</a>
     */
    private TLBytes fileReference;
    
    /**
     * Thumbnail size to download the thumbnail
     */
    private String thumbSize;

    public TLInputDocumentFileLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return this.accessHash;
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
        return "inputDocumentFileLocation#bad07584";
    }

}