package org.javagram.api.privacy.base.privacykey;

/**
 * Whether people can add you to their contact list by your phone number
 * privacyKeyAddedByPhone#42ffd42b = PrivacyKey;
 */
public class TLPrivacyKeyAddedByPhone extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x42ffd42b;

    public TLPrivacyKeyAddedByPhone() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyAddedByPhone#42ffd42b";
    }

}