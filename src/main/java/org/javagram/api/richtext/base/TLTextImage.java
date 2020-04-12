package org.javagram.api.richtext.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Inline image
 * textImage#81ccf4f document_id:long w:int h:int = RichText;
 */
public class TLTextImage extends TLAbsRichText {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x81ccf4f;

    /**
     * Document ID
     */
    private long documentId;
    
    /**
     * Width
     */
    private int w;
    
    /**
     * Height
     */
    private int h;

    public TLTextImage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.documentId, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.documentId = StreamingUtils.readLong(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "textImage#81ccf4f";
    }

}