package org.telegram.api.user.base.status;

/**
 * The type TL user status recently.
 */
public class TLUserStatusRecently extends TLAbsUserStatus {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe26f42f1;

    public TLUserStatusRecently() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "userStatusRecently#e26f42f1";
    }

}