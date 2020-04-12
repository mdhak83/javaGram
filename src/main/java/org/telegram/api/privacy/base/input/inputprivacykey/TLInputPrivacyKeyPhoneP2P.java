package org.telegram.api.privacy.base.input.inputprivacykey;

/**
 * Whether the user allows P2P communication during VoIP calls
 * inputPrivacyKeyPhoneP2P#db9e70d2 = InputPrivacyKey;
 */
public class TLInputPrivacyKeyPhoneP2P extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdb9e70d2;

    public TLInputPrivacyKeyPhoneP2P() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyPhoneP2P#db9e70d2";
    }

}