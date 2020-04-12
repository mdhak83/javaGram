package org.telegram.api.channels.base;

import org.telegram.api.channel.base.participant.TLAbsChannelParticipant;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents multiple channel participants
 * channels.channelParticipants#f56ee2a8 count:int participants:Vector&lt;ChannelParticipant&gt; users:Vector&lt;User&gt; = channels.ChannelParticipants;
 */
public class TLChannelsChannelParticipants extends TLAbsChannelsChannelParticipants {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf56ee2a8;

    /**
     * Total number of participants that correspond to the given query
     */
    private int count;
    
    /**
     * Participants
     */
    private TLVector<TLAbsChannelParticipant> participants;
    
    /**
     * Users mentioned in participant info
     */
    private TLVector<TLAbsUser> users;

    public TLChannelsChannelParticipants() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TLVector<TLAbsChannelParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(TLVector<TLAbsChannelParticipant> participants) {
        this.participants = participants;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.count, stream);
        StreamingUtils.writeTLVector(this.participants, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.count = StreamingUtils.readInt(stream);
        this.participants = StreamingUtils.readTLVector(stream, context, TLAbsChannelParticipant.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "channels.channelParticipants#f56ee2a8";
    }

}
