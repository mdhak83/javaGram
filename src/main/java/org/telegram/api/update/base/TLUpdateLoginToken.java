package org.telegram.api.update.base;

/**
 * A login token (for login via QR code) was accepted.
 * updateLoginToken#564fe691 = Update;
 */
public class TLUpdateLoginToken extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x564fe691;

    public TLUpdateLoginToken() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "updateLoginToken#564fe691";
    }

}