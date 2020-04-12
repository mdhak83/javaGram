package org.javagram.api.privacy.base.input.inputprivacykey;

/**
 * Whether messages forwarded from this user will be @see <a href="https://telegram.org/blog/unsend-privacy-emoji#anonymous-forwarding">anonymous</a>
 * inputPrivacyKeyForwards#a4dd4c08 = InputPrivacyKey;
 */
public class TLInputPrivacyKeyForwards extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa4dd4c08;

    public TLInputPrivacyKeyForwards() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyForwards#a4dd4c08";
    }

}