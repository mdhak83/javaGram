package org.javagram.api.toppeer.base.category;

/**
 * Users to which the users often forwards messages to
 * topPeerCategoryForwardUsers#a8406ca9 = TopPeerCategory;
 */
public class TLTopPeerCategoryForwardUsers extends TLAbsTopPeerCategory {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa8406ca9;

    public TLTopPeerCategoryForwardUsers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryForwardUsers#a8406ca9";
    }

}