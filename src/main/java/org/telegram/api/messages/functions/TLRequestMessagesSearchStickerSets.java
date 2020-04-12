package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.messages.base.stickers.found.TLAbsMessagesFoundStickerSets;

/**
 * Search for stickersets
 * messages.searchStickerSets#c2b7d08b flags:# exclude_featured:flags.0?true q:string hash:int = messages.FoundStickerSets;
 */
public class TLRequestMessagesSearchStickerSets extends TLMethod<TLAbsMessagesFoundStickerSets> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc2b7d08b;

    private static final int FLAG_EXCLUDE_FEATURED  = 0x00000001; //  0 : Exclude featured stickersets from results

    /**
     * Query string
     */
    private String q;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Hash for pagination, for more info click here</a>
     */
    private int hash;

    public TLRequestMessagesSearchStickerSets() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isExcludeFeatured() {
        return this.isFlagSet(FLAG_EXCLUDE_FEATURED);
    }

    public void setExcludeFeatured(boolean value) {
        this.setFlag(FLAG_EXCLUDE_FEATURED, value);
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.q, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.q = StreamingUtils.readTLString(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsMessagesFoundStickerSets deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesFoundStickerSets) {
            return (TLAbsMessagesFoundStickerSets) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.searchStickerSets#c2b7d08b";
    }

}