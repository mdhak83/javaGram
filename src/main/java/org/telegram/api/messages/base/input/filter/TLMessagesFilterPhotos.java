package org.telegram.api.messages.base.input.filter;

/**
 * Filter for messages containing photos.
 * inputMessagesFilterPhotos#9609a51c = MessagesFilter;
 */
public class TLMessagesFilterPhotos extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9609a51c;

    public TLMessagesFilterPhotos() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterPhotos#9609a51c";
    }

}