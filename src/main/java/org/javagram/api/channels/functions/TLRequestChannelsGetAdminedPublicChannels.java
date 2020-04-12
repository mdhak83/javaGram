package org.javagram.api.channels.functions;

import org.javagram.api.messages.base.chats.TLMessagesChats;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get @see <a href="https://core.telegram.org/api/channel">channels/supergroups/geogroups</a> we're admin in. Usually called when the user exceeds the @see <a href="https://core.telegram.org/constructor/config">limit</a> for owned public @see <a href="https://core.telegram.org/api/channel">channels/supergroups/geogroups</a>, and the user is given the choice to remove one of his channels/supergroups/geogroups.
 * channels.getAdminedPublicChannels#f8b036af flags:# by_location:flags.0?true check_limit:flags.1?true = messages.Chats;
 */
public class TLRequestChannelsGetAdminedPublicChannels extends TLMethod<TLMessagesChats> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf8b036af;

    private static final int FLAG_BY_LOCATION   = 0x00000001; // 0 : Get geogroups
    private static final int FLAG_CHECK_LIMIT   = 0x00000002; // 1 : If set and the user has reached the limit of owned public @see <a href="https://core.telegram.org/api/channel">channels/supergroups/geogroups</a>, instead of returning the channel list one of the specified @see <a href="https://core.telegram.org/method/channels.getAdminedPublicChannels#possible-errors">errors</a> will be returned.<br/>Useful to check if a new public channel can indeed be created, even before asking the user to enter a channel username to use in @see <a href="https://core.telegram.org/method/channels.checkUsername">channels.checkUsername</a>/@see <a href="https://core.telegram.org/method/channels.updateUsername">channels.updateUsername</a>.

    public TLRequestChannelsGetAdminedPublicChannels() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isByLocation() {
        return this.isFlagSet(FLAG_BY_LOCATION);
    }

    public void setByLocation(boolean value) {
        this.setFlag(FLAG_BY_LOCATION, value);
    }

    public boolean isCheckLimit() {
        return this.isFlagSet(FLAG_CHECK_LIMIT);
    }

    public void setCheckLimit(boolean value) {
        this.setFlag(FLAG_CHECK_LIMIT, value);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public TLMessagesChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesChats) {
            return (TLMessagesChats) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.getAdminedPublicChannels#f8b036af";
    }

}