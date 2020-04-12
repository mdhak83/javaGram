package org.javagram.api.privacy.base.privacykey;

/**
 * Whether the profile picture of the user is visible
 * privacyKeyProfilePhoto#96151fed = PrivacyKey;
 */
public class TLPrivacyKeyProfilePhoto extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x96151fed;

    public TLPrivacyKeyProfilePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyProfilePhoto#96151fed";
    }

}