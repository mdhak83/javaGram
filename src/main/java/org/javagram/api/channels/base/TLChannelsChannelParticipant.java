package org.javagram.api.channels.base;

import org.javagram.api.channel.base.participant.TLAbsChannelParticipant;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Channel participants
 * @since 24/SEP/2015
 */
public class TLChannelsChannelParticipant extends TLObject {
    public static final int CLASS_ID = 0xd0d9b163;

    private TLAbsChannelParticipant participant;
    private TLVector<TLAbsUser> users;

    public TLChannelsChannelParticipant() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }

    public TLAbsChannelParticipant getParticipant() {
        return participant;
    }

    public void setParticipant(TLAbsChannelParticipant participant) {
        this.participant = participant;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(participant, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.participant = StreamingUtils.readTLObject(stream, context, TLAbsChannelParticipant.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "channel.TLChannelParticipant#d0d9b163";
    }

}
