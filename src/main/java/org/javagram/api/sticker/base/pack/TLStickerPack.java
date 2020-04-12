package org.javagram.api.sticker.base.pack;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL sticker pack.
 * @author Ruben Bermudez
 * @version 2.0
 * TLStickerPack
 * @since 09/JAN/2015
 */
public class TLStickerPack extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x12b299d4;

    private String emoticon;
    private TLLongVector documents;

    public TLStickerPack() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets emoticon.
     *
     * @return the emoticon
     */
    public String getEmoticon() {
        return this.emoticon;
    }

    /**
     * Sets emoticon.
     *
     * @param emoticon the emoticon
     */
    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    /**
     * Gets documents.
     *
     * @return the documents
     */
    public TLLongVector getDocuments() {
        return this.documents;
    }

    /**
     * Sets documents.
     *
     * @param documents the documents
     */
    public void setDocuments(TLLongVector documents) {
        this.documents = documents;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.emoticon, stream);
        StreamingUtils.writeTLVector(this.documents, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.emoticon = StreamingUtils.readTLString(stream);
        this.documents = StreamingUtils.readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "stickerPack#12b299d4";
    }

}
