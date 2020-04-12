package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.participant.TLAbsChannelParticipant;
import org.telegram.utils.StreamingUtils;

/**
 * A user was invited to the group
 * channelAdminLogEventActionParticipantInvite#e31c34d8 participant:ChannelParticipant = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionParticipantInvite extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe31c34d8;

    /**
     * The user that was invited
     */
    private TLAbsChannelParticipant participant;

    public TLChannelAdminLogEventActionParticipantInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsChannelParticipant getParticipant() {
        return participant;
    }

    public void setParticipant(TLAbsChannelParticipant participant) {
        this.participant = participant;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.participant, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.participant = StreamingUtils.readTLObject(stream, context, TLAbsChannelParticipant.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionParticipantInvite#e31c34d8";
    }

}