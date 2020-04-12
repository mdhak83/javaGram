package org.telegram.api.privacy.base.input.inputprivacykey;

/**
 * Whether people will be able to see the user's phone number
 * inputPrivacyKeyPhoneNumber#352dafa = InputPrivacyKey;
 */
public class TLInputPrivacyKeyPhoneNumber extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x352dafa;

    public TLInputPrivacyKeyPhoneNumber() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyPhoneNumber#352dafa";
    }

}