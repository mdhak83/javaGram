package org.telegram.api.privacy.base.input.inputprivacykey;

/**
 * Whether we can see the exact last online timestamp of the user
 * inputPrivacyKeyStatusTimestamp#4f96cb18 = InputPrivacyKey;
 */
public class TLInputPrivacyKeyStatusTimestamp extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4f96cb18;

    public TLInputPrivacyKeyStatusTimestamp() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyStatusTimestamp#4f96cb18";
    }

}