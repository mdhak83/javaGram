package org.javagram.api.privacy.base.privacykey;

/**
 * Whether the user can be invited to chats
 * privacyKeyChatInvite#500e6dfa = PrivacyKey;
 */
public class TLPrivacyKeyChatInvite extends TLAbsPrivacyKey {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x500e6dfa;

    public TLPrivacyKeyChatInvite() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "privacyKeyChatInvite#500e6dfa";
    }

}