package org.javagram.api.messages.base.input.filter;

/**
 * Return only messages containing gifs
 * inputMessagesFilterGif#ffc86587 = MessagesFilter;
 */
public class TLMessagesFilterGif extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xffc86587;

    public TLMessagesFilterGif() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterGif#ffc86587";
    }
    
}