package org.javagram.api.secure.base.valuetype;

/**
 * Email
 * secureValueTypeEmail#8e3ca7ee = SecureValueType;
 */
public class TLSecureValueTypeEmail extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8e3ca7ee;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeEmail#8e3ca7ee";
    }
    
}