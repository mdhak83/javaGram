package org.telegram.api.toppeer.base.category;

/**
 * Users we've chatted most frequently with
 * topPeerCategoryCorrespondents#637b7ed = TopPeerCategory;
 */
public class TLTopPeerCategoryCorrespondents extends TLAbsTopPeerCategory {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x637b7ed;

    public TLTopPeerCategoryCorrespondents() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryCorrespondents#637b7ed";
    }

}