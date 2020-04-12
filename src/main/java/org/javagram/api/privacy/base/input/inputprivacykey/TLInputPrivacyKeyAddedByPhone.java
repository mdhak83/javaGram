package org.javagram.api.privacy.base.input.inputprivacykey;

/**
 * Whether people can add you to their contact list by your phone number
 * inputPrivacyKeyAddedByPhone#d1219bdd = InputPrivacyKey;
 */
public class TLInputPrivacyKeyAddedByPhone extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd1219bdd;

    public TLInputPrivacyKeyAddedByPhone() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyAddedByPhone#d1219bdd";
    }

}