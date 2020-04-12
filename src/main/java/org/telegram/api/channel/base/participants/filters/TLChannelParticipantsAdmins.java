package org.telegram.api.channel.base.participants.filters;

/**
 * Fetch only admin participants
 * channelParticipantsAdmins#b4608969 = ChannelParticipantsFilter;
 */
public class TLChannelParticipantsAdmins extends TLAbsChannelParticipantsFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb4608969;

    public TLChannelParticipantsAdmins() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channelParticipantsAdmins#b4608969";
    }

}