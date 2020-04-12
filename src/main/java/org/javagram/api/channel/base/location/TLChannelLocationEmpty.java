package org.javagram.api.channel.base.location;

public class TLChannelLocationEmpty extends TLAbsChannelLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbfb5ad8b;

    public TLChannelLocationEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "channelLocationEmpty#bfb5ad8b";
    }

}