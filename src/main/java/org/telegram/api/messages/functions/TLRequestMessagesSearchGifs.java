package org.telegram.api.messages.functions;

import org.telegram.api.foundgif.base.TLAbsFoundGif;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Search for GIFs
 * messages.searchGifs#bf9a776b q:string offset:int = messages.FoundGifs;
 */
public class TLRequestMessagesSearchGifs extends TLMethod<TLAbsFoundGif> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbf9a776b;

    /**
     * Text query
     */
    private String q;

    /**
     * Offset for @see <a href="https://core.telegram.org/api/offsets">pagination »</a>
     */
    private int offset;

    public TLRequestMessagesSearchGifs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.q, stream);
        StreamingUtils.writeInt(this.offset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        q = StreamingUtils.readTLString(stream);
        offset = StreamingUtils.readInt(stream);

    }

    @Override
    public TLAbsFoundGif deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsFoundGif) {
            return (TLAbsFoundGif) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsFoundGif.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.searchGifs#bf9a776b";
    }

}