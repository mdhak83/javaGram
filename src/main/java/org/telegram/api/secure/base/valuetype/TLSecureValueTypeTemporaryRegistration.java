package org.telegram.api.secure.base.valuetype;

/**
 * Temporary registration
 * secureValueTypeTemporaryRegistration#ea02ec33 = SecureValueType;
 */
public class TLSecureValueTypeTemporaryRegistration extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xea02ec33;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeTemporaryRegistration#ea02ec33";
    }
    
}