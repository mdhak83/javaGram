package org.javagram.api.channels.functions;

import org.javagram.api.channels.base.TLChannelsChannelParticipants;
import org.javagram.api.channel.base.participants.filters.TLAbsChannelParticipantsFilter;
import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get the participants of a channel
 * channels.getParticipants#123e05e9 channel:InputChannel filter:ChannelParticipantsFilter offset:int limit:int hash:int = channels.ChannelParticipants;
 */
public class TLRequestChannelsGetParticipants extends TLMethod<TLChannelsChannelParticipants> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x123e05e9;

    /**
     * Channel
     */
    private TLAbsInputChannel channel;
    
    /**
     * Which participant types to fetch
     */
    private TLAbsChannelParticipantsFilter filter;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets">Offset</a>
     */
    private int offset;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets">Limit</a>
     */
    private int limit;

    
    /**
     * @see <a href="https://core.telegram.org/api/offsets">Hash</a>
     */
    private int hash;
    
    public TLRequestChannelsGetParticipants() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsChannelParticipantsFilter getFilter() {
        return filter;
    }

    public void setFilter(TLAbsChannelParticipantsFilter filter) {
        this.filter = filter;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.filter, stream);
        StreamingUtils.writeInt(this.offset, stream);
        StreamingUtils.writeInt(this.limit, stream);
        StreamingUtils.writeInt(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.filter = StreamingUtils.readTLObject(stream, context, TLAbsChannelParticipantsFilter.class);
        this.offset = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readInt(stream);
    }

    @Override
    public TLChannelsChannelParticipants deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLChannelsChannelParticipants) {
            return (TLChannelsChannelParticipants) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLChannelsChannelParticipants.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.getParticipants#123e05e9";
    }

}