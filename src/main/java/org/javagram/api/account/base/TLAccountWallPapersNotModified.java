package org.javagram.api.account.base;

/**
 * No new wallpapers were found
 * account.wallPapersNotModified#1c199183 = account.WallPapers;
 */
public class TLAccountWallPapersNotModified extends TLAbsAccountWallPapers {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x1c199183;

    public TLAccountWallPapersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "account.wallPapersNotModified#1c199183";
    }

}