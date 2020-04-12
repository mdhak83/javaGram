package org.javagram.api.secure.base.valuetype;

/**
 * Address
 * secureValueTypeAddress#cbe31e26 = SecureValueType;
 */
public class TLSecureValueTypeAddress extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcbe31e26;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeAddress#cbe31e26";
    }
    
}