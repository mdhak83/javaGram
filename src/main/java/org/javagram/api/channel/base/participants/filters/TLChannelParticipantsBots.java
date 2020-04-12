package org.javagram.api.channel.base.participants.filters;

/**
 * Fetch only bot participants
 * channelParticipantsBots#b0d1865b = ChannelParticipantsFilter;
 */
public class TLChannelParticipantsBots extends TLAbsChannelParticipantsFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb0d1865b;

    public TLChannelParticipantsBots() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channelParticipantsBots#b0d1865b";
    }

}