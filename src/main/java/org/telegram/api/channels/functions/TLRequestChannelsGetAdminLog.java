package org.telegram.api.channels.functions;

import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.admin.logevent.TLChannelAdminLogEventsFilter;
import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api._primitives.TLVector;

/**
 * Get the admin log of a @see <a href="https://core.telegram.org/api/channel">supergroup/channel</a>
 * channels.getAdminLog#33ddf480 flags:# channel:InputChannel q:string events_filter:flags.0?ChannelAdminLogEventsFilter admins:flags.1?Vector&lt;InputUser&gt; max_id:long min_id:long limit:int = channels.AdminLogResults;
 */
public class TLRequestChannelsGetAdminLog extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x33ddf480;

    private static final int FLAG_EVENTS_FILTER = 0x00000001; // 0 : Event filter
    private static final int FLAG_ADMINS        = 0x00000002; // 1 : Only show events from these admins

    /**
     * Channel
     */
    private TLAbsInputChannel channel;
    
    /**
     * Search query, can be empty
     */
    private String q;
    
    /**
     * Event filter
     */
    private TLChannelAdminLogEventsFilter eventsFilter;
    
    /**
     * Only show events from these admins
     */
    private TLVector<TLAbsInputUser> admins;
    
    /**
     * Maximum ID of message to return (see @see <a href="https://core.telegram.org/api/offsets">pagination</a>)
     */
    private long maxId;

    /**
     * Minimum ID of message to return (see @see <a href="https://core.telegram.org/api/offsets">pagination</a>)
     */
    private long minId;

    /**
     * Maximum number of results to return, @see <a href="https://core.telegram.org/api/offsets">see pagination</a>
     */
    private int limit;

    public TLRequestChannelsGetAdminLog() {
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

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public boolean hasEventsFilter() {
        return this.isFlagSet(FLAG_EVENTS_FILTER);
    }

    public TLChannelAdminLogEventsFilter getEventsFilter() {
        return eventsFilter;
    }

    public void setEventsFilter(TLChannelAdminLogEventsFilter eventsFilter) {
        this.eventsFilter = eventsFilter;
        this.setFlag(FLAG_EVENTS_FILTER, this.eventsFilter != null);
    }

    public boolean hasAdmins() {
        return this.isFlagSet(FLAG_ADMINS);
    }

    public TLVector<TLAbsInputUser> getAdmins() {
        return admins;
    }

    public void setAdmins(TLVector<TLAbsInputUser> admins) {
        this.admins = admins;
        this.setFlag(FLAG_ADMINS, this.admins != null && !this.admins.isEmpty());
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public long getMinId() {
        return minId;
    }

    public void setMinId(long minId) {
        this.minId = minId;
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
        StreamingUtils.writeTLString(this.q, stream);
        if (this.hasEventsFilter()) {
            StreamingUtils.writeTLObject(this.eventsFilter, stream);
        }
        if (this.hasAdmins()) {
            StreamingUtils.writeTLVector(this.admins, stream);
        }
        StreamingUtils.writeLong(this.maxId, stream);
        StreamingUtils.writeLong(this.minId, stream);
        StreamingUtils.writeInt(this.limit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.q = StreamingUtils.readTLString(stream);
        if (this.hasEventsFilter()) {
            this.eventsFilter = StreamingUtils.readTLObject(stream, context, TLChannelAdminLogEventsFilter.class);
        }
        if (this.hasAdmins()) {
            this.admins = StreamingUtils.readTLVector(stream, context, TLAbsInputUser.class);
        }
        this.maxId = StreamingUtils.readLong(stream);
        this.minId = StreamingUtils.readLong(stream);
        this.limit = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return "channels.getAdminLog#33ddf480";
    }

}