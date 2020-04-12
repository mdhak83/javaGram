package org.javagram.api.secure.base.valuetype;

/**
 * Personal details
 * secureValueTypePersonalDetails#9d2a81e3 = SecureValueType;
 */
public class TLSecureValueTypePersonalDetails extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9d2a81e3;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypePersonalDetails#9d2a81e3";
    }
    
}