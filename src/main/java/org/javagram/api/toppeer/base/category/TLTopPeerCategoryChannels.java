package org.javagram.api.toppeer.base.category;

/**
 * Most frequently visited channels
 * topPeerCategoryChannels#161d9628 = TopPeerCategory;
 */
public class TLTopPeerCategoryChannels extends TLAbsTopPeerCategory {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x161d9628;

    public TLTopPeerCategoryChannels() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryChannels#161d9628";
    }

}