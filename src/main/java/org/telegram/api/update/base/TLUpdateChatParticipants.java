package org.telegram.api.update.base;

import org.telegram.api.chat.base.participant.chatparticipants.TLAbsChatParticipants;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update chat participants.
 */
public class TLUpdateChatParticipants extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7761198;

    private TLAbsChatParticipants participants;

    public TLUpdateChatParticipants() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets participants.
     *
     * @return the participants
     */
    public TLAbsChatParticipants getParticipants() {
        return this.participants;
    }

    /**
     * Sets participants.
     *
     * @param participants the participants
     */
    public void setParticipants(TLAbsChatParticipants participants) {
        this.participants = participants;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.participants, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.participants = StreamingUtils.readTLObject(stream, context, TLAbsChatParticipants.class);
    }

    @Override
    public String toString() {
        return "updateChatParticipants#7761198";
    }

}