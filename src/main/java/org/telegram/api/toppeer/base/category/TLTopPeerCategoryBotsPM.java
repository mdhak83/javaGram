package org.telegram.api.toppeer.base.category;

/**
 * Most used bots
 * topPeerCategoryBotsPM#ab661b5b = TopPeerCategory;
 */
public class TLTopPeerCategoryBotsPM extends TLAbsTopPeerCategory {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xab661b5b;

    public TLTopPeerCategoryBotsPM() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryBotsPM#ab661b5b";
    }

}
