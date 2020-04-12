package org.telegram.api.messages.base.stickers.faved;

import org.telegram.api.messages.base.stickers.TLAbsAllStickers;

/**
 * No new favorited stickers were found
 * messages.favedStickersNotModified#9e8fa6d3 = messages.FavedStickers;
 */
public class TLFavedStickersNotModified extends TLAbsAllStickers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9e8fa6d3;

    public TLFavedStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.favedStickersNotModified#9e8fa6d3";
    }

}