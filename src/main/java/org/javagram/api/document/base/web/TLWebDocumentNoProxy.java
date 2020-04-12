package org.javagram.api.document.base.web;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.document.base.attribute.TLAbsDocumentAttribute;
import org.javagram.api._primitives.TLVector;

/**
 * Remote document that can be downloaded without @see <a href="https://core.telegram.org/api/files">proxying through telegram</a>
 * webDocumentNoProxy#f9c8bcc6 url:string size:int mime_type:string attributes:Vector&lt;DocumentAttribute&gt; = WebDocument;
 */
public class TLWebDocumentNoProxy extends TLAbsWebDocument {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf9c8bcc6;

    /**
     * Document URL
     */
    private String url;
    
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

    public TLWebDocumentNoProxy() {
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
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeTLVector(this.attributes, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
        this.size = StreamingUtils.readInt(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
        this.attributes = StreamingUtils.readTLVector(stream, context, TLAbsDocumentAttribute.class);
    }

    @Override
    public String toString() {
        return "webDocumentNoProxy#f9c8bcc6";
    }
    
}