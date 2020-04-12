package org.telegram.api.document.base.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.document.base.attribute.TLAbsDocumentAttribute;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;

/**
 * Remote document
 * webDocument#1c570ed1 url:string access_hash:long size:int mime_type:string attributes:Vector&lt;DocumentAttribute&gt; = WebDocument;
 */
public class TLWebDocument extends TLAbsWebDocument {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c570ed1;

    /**
     * Document URL
     */
    private String url;
    
    /**
     * Access hash
     */
    private long accessHash;
    
    /**
     * File size
     */
    private int size;
    
    /**
     * MIME type
     */
    private String mimeType;
    
    /**
     * Attributes for media types
     */
    private TLVector<TLAbsDocumentAttribute> attributes;

    public TLWebDocument() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public TLVector<TLAbsDocumentAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(TLVector<TLAbsDocumentAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeTLVector(this.attributes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.size = StreamingUtils.readInt(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
        this.attributes = StreamingUtils.readTLVector(stream, context, TLAbsDocumentAttribute.class);
    }

    @Override
    public String toString() {
        return "webDocument#1c570ed1";
    }

}
