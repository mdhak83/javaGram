package org.javagram.api.channels.functions;

import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLChatAdminRights;

/**
 * Modify the admin rights of a user in a @see <a href="https://core.telegram.org/api/channel">supergroup/channel</a>.
 * channels.editAdmin#d33c8902 channel:InputChannel user_id:InputUser admin_rights:ChatAdminRights rank:string = Updates;
 */
public class TLRequestChannelsEditAdmin extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd33c8902;

    /**
     * The @see <a href="https://core.telegram.org/api/channel">supergroup/channel</a>
     */
    private TLAbsInputChannel channel;

    /**
     * The ID of the user whose admin rights should be modified
     */
    private TLAbsInputUser userId;

    /**
     * The admin rights
     */
    private TLChatAdminRights adminRights;

    /**
     * Indicates the role (rank) of the admin in the group: just an arbitrary string
     */
    private String rank;

    public TLRequestChannelsEditAdmin() {
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

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public TLChatAdminRights getAdminRights() {
        return adminRights;
    }

    public void setAdminRights(TLChatAdminRights adminRights) {
        this.adminRights = adminRights;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeTLObject(this.adminRights, stream);
        StreamingUtils.writeTLString(this.rank, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context,TLAbsInputChannel.class);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.adminRights = StreamingUtils.readTLObject(stream, context, TLChatAdminRights.class);
        this.rank = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.editAdmin#d33c8902";
    }

}