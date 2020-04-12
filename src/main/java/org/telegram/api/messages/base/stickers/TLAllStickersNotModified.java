package org.telegram.api.messages.base.stickers;

/**
 * No new stickers were found for the given query
 * messages.stickersNotModified#f1749a22 = messages.Stickers;
 */
public class TLAllStickersNotModified extends TLAbsAllStickers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe86602c3;

    public TLAllStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.allStickersNotModified#e86602c3";
    }

}