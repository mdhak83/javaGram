package org.telegram.api.privacy.base.privacykey;

/**
 * Whether P2P connections in phone calls are allowed
 * privacyKeyPhoneP2P#39491cc8 = PrivacyKey;
 */
public class TLPrivacyKeyPhoneP2P extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x39491cc8;

    public TLPrivacyKeyPhoneP2P() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyPhoneP2P#39491cc8";
    }

}