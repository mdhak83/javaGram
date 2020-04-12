package org.javagram.api.channel.base.participant;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Channel participant self
 *  * @since 18/SEP/2015
 */
public class TLChannelParticipantSelf extends TLAbsChannelParticipant {
    public static final int CLASS_ID = 0xa3289a6d;

    private int userId;
    private int inviterId;
    private int date;

    public TLChannelParticipantSelf() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getInviterId() {
        return inviterId;
    }

    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(userId, stream);
        StreamingUtils.writeInt(inviterId, stream);
        StreamingUtils.writeInt(date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        this.inviterId = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "channel.participants.TLChannelParticipantSelf#a3289a6d";
    }

}
