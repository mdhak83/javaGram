package org.javagram.api.secure.base.valuetype;

/**
 * Identity card
 * secureValueTypeIdentityCard#a0d0744b = SecureValueType;
 */
public class TLSecureValueTypeIdentityCard extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa0d0744b;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeIdentityCard#a0d0744b";
    }
    
}
