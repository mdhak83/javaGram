package org.telegram.api.secure.base.valuetype;

/**
 * Rental agreement
 * secureValueTypeRentalAgreement#8b883488 = SecureValueType;
 */
public class TLSecureValueTypeRentalAgreement extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8b883488;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeRentalAgreement#8b883488";
    }
    
}