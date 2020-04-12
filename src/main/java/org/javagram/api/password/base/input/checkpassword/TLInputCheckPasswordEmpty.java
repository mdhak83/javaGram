package org.javagram.api.password.base.input.checkpassword;

/**
 * There is no password
 * inputCheckPasswordEmpty#9880f658 = InputCheckPasswordSRP;
 */
public class TLInputCheckPasswordEmpty extends TLAbsInputCheckPasswordSRP {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9880f658;
    
    public TLInputCheckPasswordEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputCheckPasswordEmpty#9880f658";
    }

}