package org.telegram.api.updates.functions;

import org.telegram.api.channel.base.filters.TLAbsChannelMessagesFilter;
import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api.updates.base.channel.differences.TLAbsUpdatesChannelDifference;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns the difference between the current state of updates of a certain channel and transmitted.
 * updates.getChannelDifference#3173d78 flags:# force:flags.0?true channel:InputChannel filter:ChannelMessagesFilter pts:int limit:int = updates.ChannelDifference;
 */
public class TLRequestUpdatesGetChannelDifference extends TLMethod<TLAbsUpdatesChannelDifference> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3173d78;

    private static final int FLAG_FORCE    = 0x00000001;    // 0 : Set to true to skip some possibly unneeded updates and reduce server-side load

    /**
     * The channel
     */
    private TLAbsInputChannel channel;

    /**
     * Messsage filter
     */
    private TLAbsChannelMessagesFilter filter;

    /**
     * Persistent timestamp (see @see <a href="https://core.telegram.org/api/updates">updates</a>)
     */
    private int pts;

    /**
     * How many updates to fetch, max <code>100000</code>
     * Ordinary (non-bot) users are supposed to pass <code>10-100</code>
     */
    private int limit;

    public TLRequestUpdatesGetChannelDifference() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isForce() {
        return this.isFlagSet(FLAG_FORCE);
    }
    
    public void setForce(boolean value) {
        this.setFlag(FLAG_FORCE, value);
    }
    
    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsChannelMessagesFilter getFilter() {
        return filter;
    }

    public void setFilter(TLAbsChannelMessagesFilter filter) {
        this.filter = filter;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
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
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.filter, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.filter = StreamingUtils.readTLObject(stream, context, TLAbsChannelMessagesFilter.class);
        this.pts = StreamingUtils.readInt(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsUpdatesChannelDifference deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdatesChannelDifference) {
            return (TLAbsUpdatesChannelDifference) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdatesChannelDifference.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "updates.getChannelDifference#3173d78";
    }

}
