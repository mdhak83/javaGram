package org.telegram.api.user.base.status;

/**
 * The type TL user status empty.
 */
public class TLUserStatusEmpty extends TLAbsUserStatus {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9d05049;

    public TLUserStatusEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "userStatusEmpty#9d05049";
    }

}