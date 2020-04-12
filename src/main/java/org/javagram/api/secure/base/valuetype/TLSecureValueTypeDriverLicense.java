package org.javagram.api.secure.base.valuetype;

/**
 * Driver's license
 * secureValueTypeDriverLicense#6e425c4 = SecureValueType;
 */
public class TLSecureValueTypeDriverLicense extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6e425c4;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeDriverLicense#6e425c4";
    }
    
}