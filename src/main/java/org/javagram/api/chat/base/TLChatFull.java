package org.javagram.api.chat.base;

import org.javagram.api.bot.base.TLBotInfo;
import org.javagram.api.chat.base.invite.TLChatInviteExported;
import org.javagram.api.peer.base.notify.settings.TLAbsPeerNotifySettings;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.invite.TLAbsExportedChatInvite;
import org.javagram.api.chat.base.participant.chatparticipants.TLChatParticipants;
import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.api.photo.base.TLPhoto;

/**
 * chatFull
 * Detailed chat info
 * chatFull#1b7c9db3 flags:# can_set_username:flags.7?true has_scheduled:flags.8?true id:int about:string participants:ChatParticipants chat_photo:flags.2?Photo notify_settings:PeerNotifySettings exported_invite:ExportedChatInvite bot_info:flags.3?Vector&lt;BotInfo&gt; pinned_msg_id:flags.6?int folder_id:flags.11?int = ChatFull;
 */
public class TLChatFull extends TLAbsChatFull {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b7c9db3;

    private static final int FLAG_CHAT_PHOTO            = 0x00000004; // 2
    private static final int FLAG_BOT_INFO              = 0x00000008; // 3
    private static final int FLAG_PINNED_MSG_ID         = 0x00000040; // 6
    private static final int FLAG_CAN_SET_USERNAME      = 0x00000080; // 7
    private static final int FLAG_HAS_SCHEDULED         = 0x00000100; // 8
    private static final int FLAG_FOLDER_ID             = 0x00000800; // 11

    /**
     * Participant list
     */
    private TLChatParticipants participants;
    
    /**
     * Chat photo
     */
    private TLAbsPhoto chatPhoto;
    
    /**
     * Notification settings
     */
    private TLAbsPeerNotifySettings notifySettings;
    
    /**
     *Chat invite
     */
    private TLAbsExportedChatInvite exportedInvite;
    
    /**
     * Info about bots that are in this chat
     */
    private TLVector<TLBotInfo> botInfo = new TLVector<>();
    
    /**
     * Message ID of the pinned message
     */
    private int pinnedMsgId;
    
    /**
     * Folder ID
     */
    private int folderId;

    public TLChatFull() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isCanSetUsername() {
        return this.isFlagSet(FLAG_CAN_SET_USERNAME);
    }
    
    public void setCanSetUsername(boolean value) {
        this.setFlag(FLAG_CAN_SET_USERNAME, value);
    }
    
    public boolean isHasScheduled() {
        return this.isFlagSet(FLAG_HAS_SCHEDULED);
    }
    
    public void setHasScheduled(boolean value) {
        this.setFlag(FLAG_HAS_SCHEDULED, value);
    }
    
    public TLChatParticipants getParticipants() {
        return participants;
    }

    public void setParticipants(TLChatParticipants participants) {
        this.participants = participants;
    }

    private boolean hasChatPhoto() {
        return this.isFlagSet(FLAG_CHAT_PHOTO);
    }
    
    public TLAbsPhoto getChatPhoto() {
        return chatPhoto;
    }

    public void setChatPhoto(TLAbsPhoto chatPhoto) {
        this.chatPhoto = chatPhoto;
        this.setFlag(FLAG_CHAT_PHOTO, this.chatPhoto != null);
    }

    public TLAbsPeerNotifySettings getNotifySettings() {
        return notifySettings;
    }

    public void setNotifySettings(TLAbsPeerNotifySettings notifySettings) {
        this.notifySettings = notifySettings;
    }

    public TLAbsExportedChatInvite getExportedInvite() {
        return exportedInvite;
    }

    public void setExportedInvite(TLAbsExportedChatInvite exportedInvite) {
        this.exportedInvite = exportedInvite;
    }

    private boolean hasBotInfo() {
        return this.isFlagSet(FLAG_BOT_INFO);
    }
    
    public TLVector<TLBotInfo> getBotInfo() {
        return botInfo;
    }

    public void setBotInfo(TLVector<TLBotInfo> botInfo) {
        this.botInfo = botInfo;
        this.setFlag(FLAG_BOT_INFO, this.botInfo != null && !this.botInfo.isEmpty());
    }

    private boolean hasPinnedMsgId() {
        return this.isFlagSet(FLAG_PINNED_MSG_ID);
    }
    
    public int getPinnedMsgId() {
        return pinnedMsgId;
    }

    public void setPinnedMsgId(int pinnedMsgId) {
        this.pinnedMsgId = pinnedMsgId;
        this.setFlag(FLAG_PINNED_MSG_ID, this.pinnedMsgId != 0);
    }

    private boolean hasFolderId() {
        return this.isFlagSet(FLAG_FOLDER_ID);
    }
    
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
        this.setFlag(FLAG_FOLDER_ID, this.folderId != 0);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.about, stream);
        StreamingUtils.writeTLObject(this.participants, stream);
        if (this.hasChatPhoto()) {
            StreamingUtils.writeTLObject(this.chatPhoto, stream);
        }
        StreamingUtils.writeTLObject(this.notifySettings, stream);
        StreamingUtils.writeTLObject(this.exportedInvite, stream);
        if (this.hasBotInfo()) {
            StreamingUtils.writeTLVector(this.botInfo, stream);
        }
        if (this.hasPinnedMsgId()) {
            StreamingUtils.writeInt(this.pinnedMsgId, stream);
        }
        if (this.hasFolderId()) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.about = StreamingUtils.readTLString(stream);
        this.participants = StreamingUtils.readTLObject(stream, context, TLChatParticipants.class);
        if (this.hasChatPhoto()) {
            this.chatPhoto = StreamingUtils.readTLObject(stream, context, TLPhoto.class);
        }
        this.notifySettings = StreamingUtils.readTLObject(stream, context, TLAbsPeerNotifySettings.class);
        this.exportedInvite = StreamingUtils.readTLObject(stream, context, TLChatInviteExported.class);
        if (this.hasBotInfo()) {
            this.botInfo = StreamingUtils.readTLVector(stream, context, TLBotInfo.class);
        }
        if (this.hasPinnedMsgId()) {
            this.pinnedMsgId = StreamingUtils.readInt(stream);
        }
        if (this.hasFolderId()) {
            this.folderId = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "chatFull#1b7c9db3";
    }

}