package org.javagram.api.update.base;

/**
 * The type TL update channel new message
 */
public class TLUpdatePtsChanged extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3354678f;

    public TLUpdatePtsChanged() {
        super();
    }
    

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "updatePtsChanged#3354678f";
    }

}