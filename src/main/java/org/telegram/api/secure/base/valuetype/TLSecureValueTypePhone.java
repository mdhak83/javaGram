package org.telegram.api.secure.base.valuetype;

/**
 * Phone
 * secureValueTypePhone#b320aadb = SecureValueType;
 */
public class TLSecureValueTypePhone extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb320aadb;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypePhone#b320aadb";
    }
    
}