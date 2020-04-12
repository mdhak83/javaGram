package org.javagram.api.channel.base.participants.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * Fetch only kicked participants
 * channelParticipantsKicked#a3b54985 q:string = ChannelParticipantsFilter;
 */
public class TLChannelParticipantsKicked extends TLAbsChannelParticipantsFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa3b54985;
    
    /**
     * Optional filter for searching kicked participants by name (otherwise empty)
     */
    private String q = "";

    public TLChannelParticipantsKicked() {
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
        return "channelParticipantsKicked#a3b54985";
    }

}