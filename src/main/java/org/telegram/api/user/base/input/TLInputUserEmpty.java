package org.telegram.api.user.base.input;

/**
 * Empty constructor, does not define a user.
 * inputUserEmpty#b98886cf = InputUser;
 */
public class TLInputUserEmpty extends TLAbsInputUser {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb98886cf;

    public TLInputUserEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputUserEmpty#b98886cf";
    }

}