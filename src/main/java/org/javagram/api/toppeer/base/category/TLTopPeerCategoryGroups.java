package org.javagram.api.toppeer.base.category;

/**
 * Often-opened groups and supergroups
 * topPeerCategoryGroups#bd17a14a = TopPeerCategory;
 */
public class TLTopPeerCategoryGroups extends TLAbsTopPeerCategory {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbd17a14a;

    public TLTopPeerCategoryGroups() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryGroups#bd17a14a";
    }

}