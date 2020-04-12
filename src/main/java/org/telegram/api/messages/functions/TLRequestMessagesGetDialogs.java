package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.messages.base.dialogs.TLAbsDialogs;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns the current user dialog list.
 * messages.getDialogs#a0ee3b73 flags:# exclude_pinned:flags.0?true folder_id:flags.1?int offset_date:int offset_id:int offset_peer:InputPeer limit:int hash:int = messages.Dialogs;
 */
public class TLRequestMessagesGetDialogs extends TLMethod<TLAbsDialogs> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa0ee3b73;

    private static final int FLAG_EXCLUDE_PINNED    = 0x00000001; // 0 : Exclude pinned dialogs
    private static final int FLAG_FOLDER_ID         = 0x00000002; // 1

    /**
     * Folder ID
     */
    private int folderId;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Offsets for pagination, for more info click here</a>
     */
    private int offsetDate;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Offsets for pagination, for more info click here</a>
     */
    private int offsetId;

    /**
     * 	@see <a href="https://core.telegram.org/api/offsets">Offset peer for pagination</a>
     */
    private TLAbsInputPeer offsetPeer;

    /**
     * Number of list elements to be returned
     */
    private int limit;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;

    public TLRequestMessagesGetDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isExcludePinned() {
        return this.isFlagSet(FLAG_EXCLUDE_PINNED);
    }

    public void setExcludePinned(boolean value) {
        this.setFlag(FLAG_EXCLUDE_PINNED, value);
    }

    public boolean hasFolderId() {
        return this.isFlagSet(FLAG_FOLDER_ID);
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
        this.setFlag(FLAG_FOLDER_ID, this.folderId != 0);
    }

    public int getOffsetDate() {
        return offsetDate;
    }

    public void setOffsetDate(int offsetDate) {
        this.offsetDate = offsetDate;
    }

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
    }

    public TLAbsInputPeer getOffsetPeer() {
        return offsetPeer;
    }

    public void setOffsetPeer(TLAbsInputPeer offsetPeer) {
        this.offsetPeer = offsetPeer;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int value) {
        this.limit = value;
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
        if (this.hasFolderId()) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
        StreamingUtils.writeInt(this.offsetDate, stream);
        StreamingUtils.writeInt(this.offsetId, stream);
        StreamingUtils.writeTLObject(this.offsetPeer, stream);
        StreamingUtils.writeInt(this.limit, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasFolderId()) {
            this.folderId = StreamingUtils.readInt(stream);
        }
        this.offsetDate = StreamingUtils.readInt(stream);
        this.offsetId = StreamingUtils.readInt(stream);
        this.offsetPeer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.limit = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsDialogs deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsDialogs) {
            return (TLAbsDialogs) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsDialogs.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getDialogs#a0ee3b73";
    }

}