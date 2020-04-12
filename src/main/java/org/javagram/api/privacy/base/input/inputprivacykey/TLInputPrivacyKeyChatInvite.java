package org.javagram.api.privacy.base.input.inputprivacykey;

/**
 * Whether the user can be invited to chats
 * inputPrivacyKeyChatInvite#bdfb0426 = InputPrivacyKey;
 */
public class TLInputPrivacyKeyChatInvite extends TLAbsInputPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbdfb0426;

    public TLInputPrivacyKeyChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPrivacyKeyChatInvite#bdfb0426";
    }

}