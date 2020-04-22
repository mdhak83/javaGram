package org.javagram.api.webpage.base;

public class TLWebPageNotModified extends TLAbsWebPage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x85849473;

    public TLWebPageNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "webPageNotModified#85849473";
    }

}
