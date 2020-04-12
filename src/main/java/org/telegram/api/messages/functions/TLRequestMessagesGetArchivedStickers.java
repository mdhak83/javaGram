package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.TLMessagesArchivedStickers;
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
public class TLRequestMessagesGetArchivedStickers extends TLMethod<TLMessagesArchivedStickers> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x57f17692;

    private static final int FLAG_MASKS    = 0x00000001; // 0

    private int flags;
    private long offsetId;
    private int limit;

    public TLRequestMessagesGetArchivedStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLMessagesArchivedStickers deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesArchivedStickers) {
            return (TLMessagesArchivedStickers) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesArchivedStickers.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(long offsetId) {
        this.offsetId = offsetId;
    }

    public void enableMasks(boolean enabled) {
        this.setFlag(FLAG_MASKS, enabled);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(offsetId, stream);
        StreamingUtils.writeInt(limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        offsetId = StreamingUtils.readLong(stream);
        limit = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.getArchivedStickers#57f17692";
    }

}