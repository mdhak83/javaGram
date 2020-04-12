package org.telegram.api.channel.base.participants.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * Fetch only banned participants
 * channelParticipantsBanned#1427a5e1 q:string = ChannelParticipantsFilter;
 */
public class TLChannelParticipantsBanned extends TLAbsChannelParticipantsFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1427a5e1;
    
    /**
     * Optional filter for searching banned participants by name (otherwise empty)
     */
    private String q = "";

    public TLChannelParticipantsBanned() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(q, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.q = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "channelParticipantsBanned#1427a5e1";
    }

}