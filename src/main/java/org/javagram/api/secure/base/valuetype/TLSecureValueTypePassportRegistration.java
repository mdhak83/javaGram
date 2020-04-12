package org.javagram.api.secure.base.valuetype;

/**
 * Internal registration @see <a href="https://core.telegram.org/passport">passport</a>
 * secureValueTypePassportRegistration#99e3806a = SecureValueType;
 */
public class TLSecureValueTypePassportRegistration extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x99e3806a;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypePassportRegistration#99e3806a";
    }
    
}