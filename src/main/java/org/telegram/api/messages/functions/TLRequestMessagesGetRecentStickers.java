package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.stickers.featured.TLAbsMessagesFeaturedStickers;
import org.telegram.api.messages.base.stickers.recent.TLAbsMessagesRecentStickers;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesGetRecentStickers extends TLMethod<TLAbsMessagesRecentStickers> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5ea192c9;

    private static final int FLAG_ATTACHED = 0x00000001; // 0

    private int flags;
    private int hash;

    public TLRequestMessagesGetRecentStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsMessagesRecentStickers deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesRecentStickers) {
            return (TLAbsMessagesRecentStickers) res;
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

    public void enableAttached(boolean enabled) {
        this.setFlag(FLAG_ATTACHED, enabled);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        hash = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.getRecentStickers#5ea192c9";
    }

}