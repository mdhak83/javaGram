package org.javagram.api.channels.base;

/**
 * No new participant info could be found
 * channels.channelParticipantsNotModified#f0173fe9 = channels.ChannelParticipants;
 */
public class TLChannelsChannelParticipantsNotModified extends TLAbsChannelsChannelParticipants {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf0173fe9;

    public TLChannelsChannelParticipantsNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channels.channelParticipantsNotModified#f0173fe9";
    }
    
}