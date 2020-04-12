package org.telegram.api.secure.base.valuetype;

/**
 * Passport
 * secureValueTypePassport#3dac6a00 = SecureValueType;
 */
public class TLSecureValueTypePassport extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3dac6a00;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypePassport#3dac6a00";
    }
    
}