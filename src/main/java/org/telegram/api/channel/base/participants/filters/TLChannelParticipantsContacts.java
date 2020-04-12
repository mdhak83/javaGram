package org.telegram.api.channel.base.participants.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * Fetch only participants that are also contacts
 * channelParticipantsContacts#bb6ae88d q:string = ChannelParticipantsFilter;
 */
public class TLChannelParticipantsContacts extends TLAbsChannelParticipantsFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbb6ae88d;
    
    /**
     * Optional search query for searching contact participants by name
     */
    private String q;

    public TLChannelParticipantsContacts() {
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
        return "channelParticipantsContacts#bb6ae88d";
    }

}