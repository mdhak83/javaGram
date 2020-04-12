package org.javagram.api.privacy.base.input.inputprivacyrule;

/**
 * Disallow only contacts
 * inputPrivacyValueDisallowContacts#ba52007 = InputPrivacyRule;
 */
public class TLInputPrivacyValueDisallowContacts extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xba52007;

    public TLInputPrivacyValueDisallowContacts() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyValueDisallowContacts#ba52007";
    }

}