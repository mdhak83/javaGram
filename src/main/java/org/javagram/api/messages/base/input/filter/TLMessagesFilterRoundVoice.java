package org.javagram.api.messages.base.input.filter;

/**
 * Return only round videos and voice notes
 * inputMessagesFilterRoundVoice#7a7c17a4 = MessagesFilter;
 */
public class TLMessagesFilterRoundVoice extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7a7c17a4;

    public TLMessagesFilterRoundVoice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterRoundVoice#7a7c17a4";
    }
    
}