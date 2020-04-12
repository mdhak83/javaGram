package org.javagram.api.messages.functions;

import org.javagram.api.messages.base.stickers.TLAbsAllStickers;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get all installed stickers
 * messages.getAllStickers#1c9618b1 hash:int = messages.AllStickers;
 */
public class TLRequestMessagesGetAllStickers extends TLMethod<TLAbsAllStickers> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c9618b1;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    protected int hash;

    public TLRequestMessagesGetAllStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return this.hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
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
    public TLAbsAllStickers deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsAllStickers) {
            return (TLAbsAllStickers) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsAllStickers.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getAllStickers#1c9618b1";
    }

}