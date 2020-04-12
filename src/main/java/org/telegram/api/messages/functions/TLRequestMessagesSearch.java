package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.input.filter.TLAbsMessagesFilter;
import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.messages.base.TLAbsMessages;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.user.base.input.TLAbsInputUser;

/**
 * Gets back found messages
 * messages.search#8614ef68 flags:# peer:InputPeer q:string from_id:flags.0?InputUser filter:MessagesFilter min_date:int max_date:int offset_id:int add_offset:int limit:int max_id:int min_id:int hash:int = messages.Messages;
 */
public class TLRequestMessagesSearch extends TLMethod<TLAbsMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8614ef68;

    private static final int FLAG_FROM_ID = 0x00000001; // 0 : Only return messages sent by the specified user ID

    /**
     * User or chat, histories with which are searched, or (@see <a href="https://core.telegram.org/constructor/inputPeerEmpty">inputPeerEmpty</a>) constructor for global search
     */
    private TLAbsInputPeer peer;

    /**
     * Text search request
     */
    private String q;

    /**
     * Only return messages sent by the specified user ID
     */
    private TLAbsInputUser fromId;

    /**
     * Filter to return only specified message types
     */
    private TLAbsMessagesFilter filter;

    /**
     * If a positive value was transferred, only messages with a sending date bigger than the transferred one will be returned
     */
    private int minDate;

    /**
     * If a positive value was transferred, only messages with a sending date smaller than the transferred one will be returned
     */
    private int maxDate;

    /**
     * Only return messages starting from the specified message ID
     */
    private int offsetId;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Additional offset</a>
     */
    private int addOffset;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Number of results to return</a>
     */
    private int limit;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Maximum message ID to return</a>
     */
    private int maxId;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Minimum message ID to return</a>
     */
    private int minId;

    /**
     * @see <a href="https://core.telegram.org/api/offsets">Hash</a>
     */
    private int hash;

    public TLRequestMessagesSearch() {
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

    public String getQ() {
        return this.q;
    }

    public void setQ(String value) {
        this.q = value;
    }

    public boolean hasFromId() {
        return this.isFlagSet(FLAG_FROM_ID);
    }

    public TLAbsInputUser getFromId() {
        return fromId;
    }

    public void setFromId(TLAbsInputUser fromId) {
        this.fromId = fromId;
        this.setFlag(FLAG_FROM_ID, this.fromId != null);
    }

    public TLAbsMessagesFilter getFilter() {
        return this.filter;
    }

    public void setFilter(TLAbsMessagesFilter value) {
        this.filter = value;
    }

    public int getMinDate() {
        return this.minDate;
    }

    public void setMinDate(int value) {
        this.minDate = value;
    }

    public int getMaxDate() {
        return this.maxDate;
    }

    public void setMaxDate(int value) {
        this.maxDate = value;
    }

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
    }

    public int getAddOffset() {
        return addOffset;
    }

    public void setAddOffset(int addOffset) {
        this.addOffset = addOffset;
    }

    public int getMaxId() {
        return this.maxId;
    }

    public void setMaxId(int value) {
        this.maxId = value;
    }

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
    
    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int value) {
        this.limit = value;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLString(this.q, stream);
        if (this.hasFromId()) {
            StreamingUtils.writeTLObject(this.fromId, stream);
        }
        StreamingUtils.writeTLObject(this.filter, stream);
        StreamingUtils.writeInt(this.minDate, stream);
        StreamingUtils.writeInt(this.maxDate, stream);
        StreamingUtils.writeInt(this.offsetId, stream);
        StreamingUtils.writeInt(this.addOffset, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.minId, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.q = StreamingUtils.readTLString(stream);
        if (this.hasFromId()) {
            this.fromId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        }
        this.filter = StreamingUtils.readTLObject(stream, context, TLAbsMessagesFilter.class);
        this.minDate = StreamingUtils.readInt(stream);
        this.maxDate = StreamingUtils.readInt(stream);
        this.offsetId = StreamingUtils.readInt(stream);
        this.addOffset = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
        this.minId = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessages)  {
            return (TLAbsMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAbsMessages, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.search#8614ef68";
    }

}