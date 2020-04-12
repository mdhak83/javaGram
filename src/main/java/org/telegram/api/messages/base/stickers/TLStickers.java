package org.telegram.api.messages.base.stickers;

import org.telegram.api.document.base.TLDocument;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL stickers.
 * @author Ruben Bermudez
 * @version 2.0
 * TLStickers
 * @since 09/JAN/2015
 */
public class TLStickers extends TLAbsStickers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe4599bbd;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private String hash;
    
    /**
     * Stickers
     */
    private TLVector<TLDocument> documents;

    public TLStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Gets documents.
     *
     * @return the documents
     */
    public TLVector<TLDocument> getDocuments() {
        return this.documents;
    }

    /**
     * Sets documents.
     *
     * @param documents the documents
     */
    public void setDocuments(TLVector<TLDocument> documents) {
        this.documents = documents;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.hash, stream);
        StreamingUtils.writeTLVector(this.documents, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readTLString(stream);
        this.documents = StreamingUtils.readTLVector(stream, context, TLDocument.class);
    }

    @Override
    public String toString() {
        return "messages.stickers#e4599bbd";
    }

}
