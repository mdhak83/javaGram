package org.javagram.api.privacy.base.input.inputprivacyrule;

/**
 * Disallow all
 * inputPrivacyValueDisallowAll#d66b66c9 = InputPrivacyRule;
 */
public class TLInputPrivacyValueDisallowAll extends TLAbsInputPrivacyRule {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd66b66c9;

    public TLInputPrivacyValueDisallowAll() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyValueDisallowAll#d66b66c9";
    }
    
}