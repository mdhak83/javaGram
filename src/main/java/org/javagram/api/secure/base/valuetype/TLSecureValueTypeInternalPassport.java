package org.javagram.api.secure.base.valuetype;

/**
 * Internal @see <a href="https://core.telegram.org/passport">passport</a>
 * secureValueTypeInternalPassport#99a48f23 = SecureValueType;
 */
public class TLSecureValueTypeInternalPassport extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x99a48f23;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeInternalPassport#99a48f23";
    }
    
}