package org.telegram.api.user.base.status;

/**
 * The type TL user status last month.
 */
public class TLUserStatusLastMonth extends TLAbsUserStatus {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x77ebc742;

    public TLUserStatusLastMonth() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "userStatusLastMonth#77ebc742";
    }

}