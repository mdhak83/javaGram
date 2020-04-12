package org.javagram.api.privacy.base.input.inputprivacyrule;

/**
 * Allow only contacts
 * inputPrivacyValueAllowContacts#d09e07b = InputPrivacyRule;
 */
public class TLInputPrivacyValueAllowContacts extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd09e07b;

    public TLInputPrivacyValueAllowContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyValueAllowContacts#d09e07b";
    }
    
}