package org.javagram.api.channel.base.participants.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * Query participants by name
 * channelParticipantsSearch#656ac4b q:string = ChannelParticipantsFilter;
 */
public class TLChannelParticipantsSearch extends TLAbsChannelParticipantsFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x656ac4b;
    
    /**
     * Optional filter for searching banned participants by name (otherwise empty)
     */
    private String q = "";

    public TLChannelParticipantsSearch() {
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
        return "channelParticipantsSearch#656ac4b";
    }

}