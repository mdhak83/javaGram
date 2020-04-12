package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.messages.base.TLAbsMessages;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Search for messages and peers globally
 * messages.searchGlobal#bf7225a4 flags:# folder_id:flags.0?int q:string offset_rate:int offset_peer:InputPeer offset_id:int limit:int = messages.Messages;
 */
public class TLRequestMessagesSearchGlobal extends TLMethod<TLAbsMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbf7225a4;

    private static final int FLAG_FOLDER_ID = 0x00000001; // 0 : Folder where to search

    /**
     * Folder where to search
     */
    private int folderId;

    /**
     * Query
     */
    private String q;

    /**
     * Initially 0, then set to the @see <a href="https://core.telegram.org/constructor/messages.messagesSlice"><code>next_rate</code> parameter of messages.messagesSlice</a>
     */
    private int offsetRate;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Offsets for pagination, for more info click here</a>
     */
    private TLAbsInputPeer offsetPeer;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Offsets for pagination, for more info click here</a>
     */
    private int offsetId;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Offsets for pagination, for more info click here</a>
     */
    private int limit;

    public TLRequestMessagesSearchGlobal() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getOffsetRate() {
        return offsetRate;
    }

    public void setOffsetRate(int offsetRate) {
        this.offsetRate = offsetRate;
    }

    public TLAbsInputPeer getOffsetPeer() {
        return offsetPeer;
    }

    public void setOffsetPeer(TLAbsInputPeer offsetPeer) {
        this.offsetPeer = offsetPeer;
    }

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.q, stream);
        StreamingUtils.writeInt(this.offsetRate, stream);
        StreamingUtils.writeTLObject(this.offsetPeer, stream);
        StreamingUtils.writeInt(this.offsetId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.q = StreamingUtils.readTLString(stream);
        this.offsetRate = StreamingUtils.readInt(stream);
        this.offsetPeer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.offsetId = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessages) {
            return (TLAbsMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsMessages.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.searchGlobal#bf7225a4";
    }

}