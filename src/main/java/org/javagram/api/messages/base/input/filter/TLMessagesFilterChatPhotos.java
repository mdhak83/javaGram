package org.javagram.api.messages.base.input.filter;

/**
 * Return only chat photo changes
 * inputMessagesFilterChatPhotos#3a20ecb8 = MessagesFilter;
 */
public class TLMessagesFilterChatPhotos extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3a20ecb8;

    public TLMessagesFilterChatPhotos() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterChatPhotos#3a20ecb8";
    }
    
}