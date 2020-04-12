package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.savedgifs.TLAbsSavedGifs;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get saved GIFs
 * messages.getSavedGifs#83bf3d52 hash:int = messages.SavedGifs;
 */
public class TLRequestMessagesGetSavedGifs extends TLMethod<TLAbsSavedGifs> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x83bf3d52;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;

    public TLRequestMessagesGetSavedGifs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsSavedGifs deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsSavedGifs) {
            return (TLAbsSavedGifs) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsSavedGifs.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getSavedGifs#83bf3d52";
    }

}