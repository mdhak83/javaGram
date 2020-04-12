package org.javagram.api.update.base;

/**
 * The type TL update channel new message
 */
public class TLUpdateRecentStickers extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9a422c20;

    public TLUpdateRecentStickers() {
        super();
    }
    

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "updateRecentStickers#9a422c20";
    }

}