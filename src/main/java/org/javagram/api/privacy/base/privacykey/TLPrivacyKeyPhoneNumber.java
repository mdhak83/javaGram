package org.javagram.api.privacy.base.privacykey;

/**
 * Whether the user allows us to see his phone number
 * privacyKeyPhoneNumber#d19ae46d = PrivacyKey;
 */
public class TLPrivacyKeyPhoneNumber extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd19ae46d;

    public TLPrivacyKeyPhoneNumber() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyPhoneNumber#d19ae46d";
    }

}