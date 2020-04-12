package org.telegram.api.toppeer.base.category;

/**
 * Chats to which the users often forwards messages to
 * topPeerCategoryForwardChats#fbeec0f0 = TopPeerCategory;
 */
public class TLTopPeerCategoryForwardChats extends TLAbsTopPeerCategory {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfbeec0f0;

    public TLTopPeerCategoryForwardChats() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "topPeerCategoryForwardChats#fbeec0f0";
    }

}