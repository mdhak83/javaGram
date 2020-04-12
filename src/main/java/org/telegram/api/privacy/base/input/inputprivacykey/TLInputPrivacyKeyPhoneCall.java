package org.telegram.api.privacy.base.input.inputprivacykey;

/**
 * Whether the user will accept phone calls
 * inputPrivacyKeyPhoneCall#fabadc5f = InputPrivacyKey;
 */
public class TLInputPrivacyKeyPhoneCall extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfabadc5f;

    public TLInputPrivacyKeyPhoneCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyPhoneCall#fabadc5f";
    }

}