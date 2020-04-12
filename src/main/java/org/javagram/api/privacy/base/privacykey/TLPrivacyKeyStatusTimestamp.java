package org.javagram.api.privacy.base.privacykey;

/**
 * Whether we can see the last online timestamp
 * privacyKeyStatusTimestamp#bc2eab30 = PrivacyKey;
 */
public class TLPrivacyKeyStatusTimestamp extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbc2eab30;

    public TLPrivacyKeyStatusTimestamp() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyStatusTimestamp#bc2eab30";
    }

}