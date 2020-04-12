package org.javagram.api.document.base;

import org.javagram.api.document.base.attribute.TLAbsDocumentAttribute;
import org.javagram.api.photo.base.size.TLAbsPhotoSize;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * Document
 */
public class TLDocument extends TLAbsDocument {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9ba29cc1;
    
    private static final int FLAG_THUMBS    = 0x00000001; // 0 : Thumbnails

    /**
     * Check sum, dependant on document ID
     */
    private long accessHash;
    
    /**
     * @see <a href="https://core.telegram.org/api/file_reference">File reference</a>
     */
    private TLBytes fileReference;

    /**
     * Creation date
     */
    private int date;
    
    /**
     * MIME type
     */
    private String mimeType;
    
    /**
     * Size
     */
    private int size;
    
    /**
     * Thumbnails
     */
    private TLVector<TLAbsPhotoSize> thumbs = new TLVector<>();
    
    /**
     * DC ID
     */
    private int dcId;
    
    /**
     * Attributes
     */
    private TLVector<TLAbsDocumentAttribute> attributes = new TLVector<>();

    public TLDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean hasThumbs() {
        return this.isFlagSet(FLAG_THUMBS);
    }

    public TLVector<TLAbsPhotoSize> getThumbs() {
        return thumbs;
    }

    public void setThumbs(TLVector<TLAbsPhotoSize> thumbs) {
        this.thumbs = thumbs;
        this.setFlag(FLAG_THUMBS, this.thumbs != null && !this.thumbs.isEmpty());
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
    }

    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(TLVector<TLAbsDocumentAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLBytes(this.fileReference, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeInt(this.size, stream);
        if (this.hasThumbs()) {
            StreamingUtils.writeTLVector(this.thumbs, stream);
        }
        StreamingUtils.writeInt(this.dcId, stream);
        StreamingUtils.writeTLVector(this.attributes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.fileReference = StreamingUtils.readTLBytes(stream, context);
        this.date = StreamingUtils.readInt(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
        this.size = StreamingUtils.readInt(stream);
        if (this.hasThumbs()) {
            this.thumbs = StreamingUtils.readTLVector(stream, context, TLAbsPhotoSize.class);
        }
        this.dcId = StreamingUtils.readInt(stream);
        this.attributes = StreamingUtils.readTLVector(stream, context, TLAbsDocumentAttribute.class);
    }

    @Override
    public String toString() {
        return "document#9ba29cc1";
    }

}