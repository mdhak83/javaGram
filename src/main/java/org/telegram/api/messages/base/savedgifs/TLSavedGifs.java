package org.telegram.api.messages.base.savedgifs;

import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
  * @since 13/FEB/2016
 */
public class TLSavedGifs extends TLObject {
    public static final int CLASS_ID = 0x2e0709a5;

    private int hash;
    private TLVector<TLAbsDocument> gifs;

    public TLSavedGifs() {
        super();
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public TLVector<TLAbsDocument> getGifs() {
        return gifs;
    }

    public void setGifs(TLVector<TLAbsDocument> gifs) {
        this.gifs = gifs;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLVector(this.gifs, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.gifs = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "savedgifs#2e0709a5";
    }

}
