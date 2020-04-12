package org.telegram.api.auth.base.codetype;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @since 16/MAR/2016
 */
public class TLCodeTypeCall extends TLAbsCodeType {
    public static final int CLASS_ID = 0x741cd3e3;

    public TLCodeTypeCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "auth.codeTypeCall#741cd3e3";
    }

}
