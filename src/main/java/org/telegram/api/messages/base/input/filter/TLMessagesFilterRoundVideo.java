package org.telegram.api.messages.base.input.filter;

/**
 * Return only round videos
 * inputMessagesFilterRoundVideo#b549da53 = MessagesFilter;
 */
public class TLMessagesFilterRoundVideo extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb549da53;

    public TLMessagesFilterRoundVideo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterRoundVideo#b549da53";
    }
    
}