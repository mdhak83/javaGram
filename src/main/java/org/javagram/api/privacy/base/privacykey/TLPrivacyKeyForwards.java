package org.javagram.api.privacy.base.privacykey;

/**
 * Whether messages forwarded from the user will be @see <a href="https://telegram.org/blog/unsend-privacy-emoji#anonymous-forwarding">anonymously forwarded</a>
 * privacyKeyForwards#69ec56a3 = PrivacyKey;
 */
public class TLPrivacyKeyForwards extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x69ec56a3;

    public TLPrivacyKeyForwards() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyForwards#69ec56a3";
    }

}