package org.telegram.api.user.base.status;

/**
 * The type TL user status last week.
 */
public class TLUserStatusLastWeek extends TLAbsUserStatus {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7bf09fc;

    public TLUserStatusLastWeek() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "userStatusLastWeek#7bf09fc";
    }

}