package org.telegram.api.messages.base.input.filter;

/**
 * Filter for messages containing photos or videos.
 * inputMessagesFilterPhotoVideo#56e9f0e4 = MessagesFilter;
 */
public class TLMessagesFilterPhotoVideo extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x56e9f0e4;

    public TLMessagesFilterPhotoVideo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterPhotoVideo#56e9f0e4";
    }

}