package org.telegram.api.privacy.base.input.inputprivacykey;

/**
 * Whether people will be able to see the user's profile picture
 * inputPrivacyKeyProfilePhoto#5719bacc = InputPrivacyKey;
 */
public class TLInputPrivacyKeyProfilePhoto extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5719bacc;

    public TLInputPrivacyKeyProfilePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyProfilePhoto#5719bacc";
    }

}