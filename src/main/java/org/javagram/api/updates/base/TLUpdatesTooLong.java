package org.javagram.api.updates.base;

/**
 * The type TL updates too long.
 */
public class TLUpdatesTooLong extends TLAbsUpdates {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe317af7e;

    public TLUpdatesTooLong() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "updatesTooLong#e317af7e";
    }

}