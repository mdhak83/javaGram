package org.telegram.api.messages.base;

import org.telegram.api.foundgif.base.TLAbsFoundGif;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL affected messages.
 * @author Ruben Bermudez
 * @version 2.0
 * TLAffectedMessages
 * @since 11/APR/2015
 */
public class TLFoundGifs extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x450a1c0a;

    private int nextOffset;
    private TLVector<TLAbsFoundGif> results;

    public TLFoundGifs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(int nextOffset) {
        this.nextOffset = nextOffset;
    }

    public TLVector<TLAbsFoundGif> getResults() {
        return results;
    }

    public void setResults(TLVector<TLAbsFoundGif> results) {
        this.results = results;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.nextOffset, stream);
        StreamingUtils.writeTLVector(this.results, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.nextOffset = StreamingUtils.readInt(stream);
        this.results = StreamingUtils.readTLVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.foundGifs#450a1c0a";
    }

}
