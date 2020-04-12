package org.javagram.api.user.base.input;

/**
 * Defines the current user.
 * inputUserSelf#f7c1b13f = InputUser;
 */
public class TLInputUserSelf extends TLAbsInputUser {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf7c1b13f;

    public TLInputUserSelf() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputUserSelf#f7c1b13f";
    }

}
