package org.telegram.api.messages.functions;

import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get a document by its SHA256 hash, mainly used for gifs
 * messages.getDocumentByHash#338e2464 sha256:bytes size:int mime_type:string = Document;
 */
public class TLRequestMessagesGetDocumentByHash extends TLMethod<TLAbsDocument> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x338e2464;

    /**
     * SHA256 of file
     */
    private TLBytes sha256;

    /**
     * Size of the file in bytes
     */
    private int size;

    /**
     * Mime type
     */
    private String mimeType;

    public TLRequestMessagesGetDocumentByHash() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getSha256() {
        return sha256;
    }

    public void setSha256(TLBytes sha256) {
        this.sha256 = sha256;
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(sha256, stream);
        StreamingUtils.writeInt(this.size, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.sha256 = StreamingUtils.readTLBytes(stream, context);
        this.size = StreamingUtils.readInt(stream);
        this.mimeType = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsDocument deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsDocument) {
            return (TLAbsDocument) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsDocument.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getDocumentByHash#338e2464";
    }

}