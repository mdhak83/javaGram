package org.telegram.api.toppeer.base.category;

/**
 * Most used inline bots
 * topPeerCategoryBotsInline#148677e2 = TopPeerCategory;
 */
public class TLTopPeerCategoryBotsInline extends TLAbsTopPeerCategory {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x148677e2;

    public TLTopPeerCategoryBotsInline() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryBotsInline#148677e2";
    }

}