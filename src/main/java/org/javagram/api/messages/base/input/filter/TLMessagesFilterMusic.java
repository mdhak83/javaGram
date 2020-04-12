package org.javagram.api.messages.base.input.filter;

/**
 * Return only messages containing audio files
 * inputMessagesFilterMusic#3751b49e = MessagesFilter;
 */
public class TLMessagesFilterMusic extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3751b49e;

    public TLMessagesFilterMusic() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterMusic#3751b49e";
    }
    
}