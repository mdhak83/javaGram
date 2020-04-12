package org.telegram.api.privacy.base.input.inputprivacyrule;

/**
 * Allow all users
 * inputPrivacyValueAllowAll#184b35ce = InputPrivacyRule;
 */
public class TLInputPrivacyValueAllowAll extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x184b35ce;

    public TLInputPrivacyValueAllowAll() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyValueAllowAll#184b35ce";
    }
    
}