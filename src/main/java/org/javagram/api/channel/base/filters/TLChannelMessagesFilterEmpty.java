package org.javagram.api.channel.base.filters;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Channel messages filter empty
 *  * @since 18/SEP/2015
 */
public class TLChannelMessagesFilterEmpty extends TLAbsChannelMessagesFilter {
    public static final int CLASS_ID = 0x94d42ee7;

    public TLChannelMessagesFilterEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channel.messages.filter.TLChannelMessagesFilterEmpty#94d42ee7";
    }

}
