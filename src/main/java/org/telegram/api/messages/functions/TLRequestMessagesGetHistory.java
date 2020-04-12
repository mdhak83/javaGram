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
 * messages.getHistory
 * Gets back the conversation history with one interlocutor / within a chat
 * messages.getHistory#dcbb8260 peer:InputPeer offset_id:int offset_date:int add_offset:int limit:int max_id:int min_id:int hash:int = messages.Messages;
 */
public class TLRequestMessagesGetHistory extends TLMethod<TLAbsMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdcbb8260;

    /**
     * Target peer
     */
    private TLAbsInputPeer peer;

    /**
     * Only return messages starting from the specified message ID
     */
    private int offsetId;

    /**
     * Only return messages sent after the specified date
     */
    private int offsetDate;

    /**
     * Number of list elements to be skipped, negative values are also accepted.
     */
    private int addOffset;

    /**
     * Number of results to return
     */
    private int limit;

    /**
     * If a positive value was transferred, the method will return only messages with IDs less than <strong>max_id</strong>
     */
    private int maxId;

    /**
     * If a positive value was transferred, the method will return only messages with IDs more than <strong>min_id</strong>
     */
    private int minId;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;

    public TLRequestMessagesGetHistory() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
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

    public int getMaxId() {
        return this.maxId;
    }

    public void setMaxId(int value) {
        this.maxId = value;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int value) {
        this.limit = value;
    }

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public int getAddOffset() {
        return addOffset;
    }

    public void setAddOffset(int addOffset) {
        this.addOffset = addOffset;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.offsetId, stream);
        StreamingUtils.writeInt(this.offsetDate, stream);
        StreamingUtils.writeInt(this.addOffset, stream);
        StreamingUtils.writeInt(this.limit, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.minId, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.offsetId = StreamingUtils.readInt(stream);
        this.offsetDate = StreamingUtils.readInt(stream);
        this.addOffset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
        this.minId = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessages) {
            return (TLAbsMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsMessages.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getHistory#dcbb8260";
    }

}