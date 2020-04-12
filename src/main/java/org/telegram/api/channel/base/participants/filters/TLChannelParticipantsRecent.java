package org.telegram.api.channel.base.participants.filters;

/**
 * Fetch only recent participants
 * channelParticipantsRecent#de3f3c79 = ChannelParticipantsFilter;
 */
public class TLChannelParticipantsRecent extends TLAbsChannelParticipantsFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xde3f3c79;

    public TLChannelParticipantsRecent() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channelParticipantsRecent#de3f3c79";
    }

}