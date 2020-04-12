package org.javagram.api.messages.base.stickers.featured;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 07/AUG/2016
 */
public class TLMessagesFeaturedStickersNotModified extends TLAbsMessagesFeaturedStickers {
    public static final int CLASS_ID = 0x4ede3cf;

    public TLMessagesFeaturedStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.featuredStickersNotModified#4ede3cf";
    }

}
