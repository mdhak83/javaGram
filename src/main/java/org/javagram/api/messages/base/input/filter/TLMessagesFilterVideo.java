package org.javagram.api.messages.base.input.filter;

/**
 * Filter for messages containing videos.
 * inputMessagesFilterVideo#9fc00e65 = MessagesFilter;
 */
public class TLMessagesFilterVideo extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9fc00e65;

    public TLMessagesFilterVideo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterVideo#9fc00e65";
    }
    
}