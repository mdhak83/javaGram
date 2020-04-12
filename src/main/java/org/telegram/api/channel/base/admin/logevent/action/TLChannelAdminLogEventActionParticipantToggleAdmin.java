package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.participant.TLAbsChannelParticipant;
import org.telegram.utils.StreamingUtils;

/**
 * The admin @see <a href="https://core.telegram.org/api/rights">rights</a> of a user were changed
 * channelAdminLogEventActionParticipantToggleAdmin#d5676710 prev_participant:ChannelParticipant new_participant:ChannelParticipant = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionParticipantToggleAdmin extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd5676710;

    /**
     * Previous admin rights
     */
    private TLAbsChannelParticipant prevParticipant;

    /**
     * New admin rights
     */
    private TLAbsChannelParticipant newParticipant;

    public TLChannelAdminLogEventActionParticipantToggleAdmin() {
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
        return "channelAdminLogEventActionParticipantToggleAdmin#d5676710";
    }

}