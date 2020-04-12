package org.javagram.api.messages.base.input.filter;

/**
 * Return only messages containing voice notes
 * inputMessagesFilterVoice#50f5c392 = MessagesFilter;
 */
public class TLMessagesFilterVoice extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x50f5c392;

    public TLMessagesFilterVoice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterVoice#50f5c392";
    }
    
}