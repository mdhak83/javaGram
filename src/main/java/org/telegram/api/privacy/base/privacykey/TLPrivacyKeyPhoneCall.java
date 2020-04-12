package org.telegram.api.privacy.base.privacykey;

/**
 * Whether the user accepts phone calls
 * privacyKeyPhoneCall#3d662b7b = PrivacyKey;
 */
public class TLPrivacyKeyPhoneCall extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3d662b7b;

    public TLPrivacyKeyPhoneCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyPhoneCall#3d662b7b";
    }

}