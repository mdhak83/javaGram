package org.javagram.api.messages.functions;

import org.javagram.api.messages.base.stickers.featured.TLAbsMessagesFeaturedStickers;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetFeaturedStickers extends TLMethod<TLAbsMessagesFeaturedStickers> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2dacca4f;

    private int hash;

    public TLRequestMessagesGetFeaturedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsMessagesFeaturedStickers deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesFeaturedStickers) {
            return (TLAbsMessagesFeaturedStickers) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsMessagesFeaturedStickers.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        hash = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.getFeaturedStickers#2dacca4f";
    }

}