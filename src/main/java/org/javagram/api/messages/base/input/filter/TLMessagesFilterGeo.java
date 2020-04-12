package org.javagram.api.messages.base.input.filter;

/**
 * Return only messages containing geolocations
 * inputMessagesFilterGeo#e7026d0d = MessagesFilter;
 */
public class TLMessagesFilterGeo extends TLAbsMessagesFilter {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe7026d0d;

    public TLMessagesFilterGeo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMessagesFilterGeo#e7026d0d";
    }

}