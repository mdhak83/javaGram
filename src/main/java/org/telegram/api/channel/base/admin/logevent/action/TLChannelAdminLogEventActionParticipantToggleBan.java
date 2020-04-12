package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.participant.TLAbsChannelParticipant;
import org.telegram.utils.StreamingUtils;

/**
 * The banned @see <a href="https://core.telegram.org/api/rights">rights</a> of a user were changed
 * channelAdminLogEventActionParticipantToggleBan#e6d83d7e prev_participant:ChannelParticipant new_participant:ChannelParticipant = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionParticipantToggleBan extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe6d83d7e;

    /**
     * Old banned rights of user
     */
    private TLAbsChannelParticipant prevParticipant;

    /**
     * New banned rights of user
     */
    private TLAbsChannelParticipant newParticipant;

    public TLChannelAdminLogEventActionParticipantToggleBan() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsChannelParticipant getPrevParticipant() {
        return prevParticipant;
    }

    public void setPrevParticipant(TLAbsChannelParticipant prevParticipant) {
        this.prevParticipant = prevParticipant;
    }

    public TLAbsChannelParticipant getNewParticipant() {
        return newParticipant;
    }

    public void setNewParticipant(TLAbsChannelParticipant newParticipant) {
        this.newParticipant = newParticipant;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.prevParticipant, stream);
        StreamingUtils.writeTLObject(this.newParticipant, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevParticipant = StreamingUtils.readTLObject(stream, context, TLAbsChannelParticipant.class);
        this.newParticipant = StreamingUtils.readTLObject(stream, context, TLAbsChannelParticipant.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionParticipantToggleBan#e6d83d7e";
    }

}