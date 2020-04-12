package org.telegram.api.chat.base.invite;

/**
 * No info is associated to the chat invite
 * chatInviteEmpty#69df3769 = ExportedChatInvite;
 */
public class TLChatInviteEmpty extends TLAbsExportedChatInvite {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x69df3769;

    public TLChatInviteEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "chat.chatInviteEmpty#69df3769";
    }

}