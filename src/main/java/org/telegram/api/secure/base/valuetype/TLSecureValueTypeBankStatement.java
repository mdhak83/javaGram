package org.telegram.api.secure.base.valuetype;

/**
 * Bank statement
 * secureValueTypeBankStatement#89137c0d = SecureValueType;
 */
public class TLSecureValueTypeBankStatement extends TLAbsSecureValueType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x89137c0d;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureValueTypeBankStatement#89137c0d";
    }
    
}