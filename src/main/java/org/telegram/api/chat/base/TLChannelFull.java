package org.telegram.api.chat.base;

import org.telegram.api.bot.base.TLBotInfo;
import org.telegram.api.peer.base.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.location.TLAbsChannelLocation;
import org.telegram.api.chat.base.invite.TLAbsExportedChatInvite;
import org.telegram.api.sticker.base.set.TLStickerSet;

/**
 * channelFull
 * Full info about a @see <a href="https://core.telegram.org/api/channel">channel/supergroup</a>
 * channelFull#2d895c74 flags:# can_view_participants:flags.3?true can_set_username:flags.6?true can_set_stickers:flags.7?true hidden_prehistory:flags.10?true can_view_stats:flags.12?true can_set_location:flags.16?true has_scheduled:flags.19?true id:int about:string participants_count:flags.0?int admins_count:flags.1?int kicked_count:flags.2?int banned_count:flags.2?int online_count:flags.13?int read_inbox_max_id:int read_outbox_max_id:int unread_count:int chat_photo:Photo notify_settings:PeerNotifySettings exported_invite:ExportedChatInvite bot_info:Vector&lt;BotInfo&gt; migrated_from_chat_id:flags.4?int migrated_from_max_id:flags.4?int pinned_msg_id:flags.5?int stickerset:flags.8?StickerSet available_min_id:flags.9?int folder_id:flags.11?int linked_chat_id:flags.14?int location:flags.15?ChannelLocation slowmode_seconds:flags.17?int slowmode_next_send_date:flags.18?int pts:int = ChatFull;
 */
public class TLChannelFull extends TLAbsChatFull {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2d895c74;

    private static final int FLAG_PARTICIPANTS_COUNT        = 0x00000001; //  0 : Number of participants of the channel
    private static final int FLAG_ADMINS_COUNT              = 0x00000002; //  1 : Number of channel admins
    private static final int FLAG_KICKED_COUNT              = 0x00000004; //  2 : Number of users @see <a href="https://core.telegram.org/api/rights">kicked</a> from the channel
    private static final int FLAG_BANNED_COUNT              = 0x00000004; //  2 : Number of users @see <a href="https://core.telegram.org/api/rights">banned</a> from the channel
    private static final int FLAG_CAN_VIEW_PARTICIPANTS     = 0x00000008; //  3 : Can we view the participant list?
    private static final int FLAG_MIGRATED_FROM_CHAT_ID     = 0x00000010; //  4 : The chat ID from which this group was @see <a href="https://core.telegram.org/api/channel">migrated</a>
    private static final int FLAG_MIGRATED_FROM_MAX_ID      = 0x00000010; //  4 : The message ID in the original chat at which this group was @see <a href="https://core.telegram.org/api/channel">migrated</a>
    private static final int FLAG_PINNED_MSG_ID             = 0x00000020; //  5 : Message ID of the pinned message
    private static final int FLAG_CAN_SET_USERNAME          = 0x00000040; //  6 : Can we set the channel's username?
    private static final int FLAG_CAN_SET_STICKERS          = 0x00000080; //  7 : Can we @see <a href="https://core.telegram.org/method/channels.setStickers">associate</a> a stickerpack to the supergroup?
    private static final int FLAG_STICKERSET                = 0x00000100; //  8 : Associated stickerset
    private static final int FLAG_AVAILABLE_MIN_ID          = 0x00000200; //  9 : Identifier of a maximum unavailable message in a channel due to hidden history.
    private static final int FLAG_HIDDEN_PREHISTORY         = 0x00000400; // 10 : Is the history before we joined hidden to us?
    private static final int FLAG_FOLDER_ID                 = 0x00000800; // 11 : Folder ID
    private static final int FLAG_CAN_VIEW_STATS            = 0x00001000; // 12 : Can the user call @see <a href="https://core.telegram.org/method/messages.getStatsURL">messages.getStatsURL</a> on this channel
    private static final int FLAG_ONLINE_COUNT              = 0x00002000; // 13 : Number of users currently online
    private static final int FLAG_LINKED_CHAT_ID            = 0x00004000; // 14 : ID of the linked discussion chat for channels
    private static final int FLAG_LOCATION                  = 0x00008000; // 15 : Location of the geogroup
    private static final int FLAG_CAN_SET_LOCATION          = 0x00010000; // 16 : Can we set the geolocation of this group (for geogroups)
    private static final int FLAG_SLOWMODE_SECONDS          = 0x00020000; // 17 : If specified, users in supergroups will only be able to send one message every <code>slowmode_seconds</code> seconds
    private static final int FLAG_SLOWMODE_NEXT_SEND_DATE   = 0x00040000; // 18 : Indicates when the user will be allowed to send another message in the supergroup (unixdate)
    private static final int FLAG_HAS_SCHEDULED             = 0x00080000; // 19 : Whether scheduled messages are available

    /**
     * Number of participants of the channel
     */
    private int participantsCount;

    /**
     * Number of channel admins
     */
    private int adminsCount;

    /**
     * Number of users @see <a href="https://core.telegram.org/api/rights">kicked</a> from the channel
     */
    private int kickedCount;

    /**
     * Number of users @see <a href="https://core.telegram.org/api/rights">banned</a> from the channel
     */
    private int bannedCount;

    /**
     * Number of users currently online
     */
    private int onlineCount;

    /**
     * Position up to which all incoming messages are read.
     */
    private int readInboxMaxId;

    /**
     * Position up to which all outgoing messages are read.
     */
    private int readOutboxMaxId;

    /**
     * Count of unread messages
     */
    private int unreadCount;

    /**
     * Channel picture
     */
    private TLAbsPhoto chatPhoto;

    /**
     * Notification settings
     */
    private TLAbsPeerNotifySettings notifySettings;

    /**
     * Invite link
     */
    private TLAbsExportedChatInvite exportedInvite;

    /**
     * Info about bots in the channel/supergrup
     */
    private TLVector<TLBotInfo> botInfo;

    /**
     * The chat ID from which this group was @see <a href="https://core.telegram.org/api/channel">migrated</a>
     */
    private int migratedFromChatId;

    /**
     * The message ID in the original chat at which this group was @see <a href="https://core.telegram.org/api/channel">migrated</a>
     */
    private int migratedFromMaxId;

    /**
     * Message ID of the pinned message
     */
    private int pinnedMsgId;

    /**
     * Associated stickerset
     */
    private TLStickerSet stickerset;

    /**
     * Identifier of a maximum unavailable message in a channel due to hidden history.
     */
    private int availableMinId;

    /**
     * Folder ID
     */
    private int folderId;

    /**
     * ID of the linked discussion chat for channels
     */
    private int linkedChatId;

    /**
     * Location of the geogroup
     */
    private TLAbsChannelLocation location;

    /**
     * If specified, users in supergroups will only be able to send one message every <code>slowmode_seconds</code> seconds
     */
    private int slowmodeSeconds;

    /**
     * Indicates when the user will be allowed to send another message in the supergroup (unixdate)
     */
    private int slowmodeNextSendDate;

    /**
     * Latest @see <a href="https://core.telegram.org/api/updates">PTS</a> for this channel
     */
    private int pts;

    public TLChannelFull() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isCanViewParticipants() {
        return (this.isFlagSet(FLAG_CAN_VIEW_PARTICIPANTS));
    }
    
    public void setCanViewParticipants(boolean value) {
        this.setFlag(FLAG_CAN_VIEW_PARTICIPANTS, value);
    }
    
    public boolean isCanSetUsername() {
        return (this.isFlagSet(FLAG_CAN_SET_USERNAME));
    }
    
    public void setCanSetUsername(boolean value) {
        this.setFlag(FLAG_CAN_SET_USERNAME, value);
    }
    
    public boolean isCanSetStickers() {
        return (this.isFlagSet(FLAG_CAN_SET_STICKERS));
    }
    
    public void setCanSetStickers(boolean value) {
        this.setFlag(FLAG_CAN_SET_STICKERS, value);
    }
    
    public boolean isHiddenPrehistory() {
        return (this.isFlagSet(FLAG_HIDDEN_PREHISTORY));
    }
    
    public void setHiddenPrehistory(boolean value) {
        this.setFlag(FLAG_HIDDEN_PREHISTORY, value);
    }
    
    public boolean isCanViewStats() {
        return (this.isFlagSet(FLAG_CAN_VIEW_STATS));
    }
    
    public void setCanViewStats(boolean value) {
        this.setFlag(FLAG_CAN_VIEW_STATS, value);
    }
    
    public boolean isCanSetLocation() {
        return (this.isFlagSet(FLAG_CAN_SET_LOCATION));
    }
    
    public void setCanSetLocation(boolean value) {
        this.setFlag(FLAG_CAN_SET_LOCATION, value);
    }
    
    public boolean isHasScheduled() {
        return (this.isFlagSet(FLAG_HAS_SCHEDULED));
    }
    
    public void setHasScheduled(boolean value) {
        this.setFlag(FLAG_HAS_SCHEDULED, value);
    }
    
    public boolean hasParticipantsCount() {
        return this.isFlagSet(FLAG_PARTICIPANTS_COUNT);
    }
    
    public int getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
        this.setFlag(FLAG_PARTICIPANTS_COUNT, this.participantsCount != 0);
    }

    public boolean hasAdminsCount() {
        return this.isFlagSet(FLAG_ADMINS_COUNT);
    }
    
    public int getAdminsCount() {
        return adminsCount;
    }

    public void setAdminsCount(int adminsCount) {
        this.adminsCount = adminsCount;
        this.setFlag(FLAG_ADMINS_COUNT, this.adminsCount != 0);
    }

    public boolean hasKickedCount() {
        return this.isFlagSet(FLAG_KICKED_COUNT);
    }
    
    public int getKickedCount() {
        return kickedCount;
    }

    public void setKickedCount(int kickedCount) {
        this.kickedCount = kickedCount;
        this.setFlag(FLAG_KICKED_COUNT, this.kickedCount != 0);
    }

    public boolean hasBannedCount() {
        return this.isFlagSet(FLAG_BANNED_COUNT);
    }
    
    public int getBannedCount() {
        return bannedCount;
    }

    public void setBannedCount(int bannedCount) {
        this.bannedCount = bannedCount;
        this.setFlag(FLAG_BANNED_COUNT, this.bannedCount != 0);
    }

    public boolean hasOnlineCount() {
        return this.isFlagSet(FLAG_ONLINE_COUNT);
    }
    
    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
        this.setFlag(FLAG_ONLINE_COUNT, this.onlineCount != 0);
    }

    public int getReadInboxMaxId() {
        return readInboxMaxId;
    }

    public void setReadInboxMaxId(int readInboxMaxId) {
        this.readInboxMaxId = readInboxMaxId;
    }

    public int getReadOutboxMaxId() {
        return readOutboxMaxId;
    }

    public void setReadOutboxMaxId(int readOutboxMaxId) {
        this.readOutboxMaxId = readOutboxMaxId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public TLAbsPhoto getChatPhoto() {
        return chatPhoto;
    }

    public void setChatPhoto(TLAbsPhoto chatPhoto) {
        this.chatPhoto = chatPhoto;
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

    public TLVector<TLBotInfo> getBotInfo() {
        return botInfo;
    }

    public void setBotInfo(TLVector<TLBotInfo> botInfo) {
        this.botInfo = botInfo;
    }

    public boolean hasMigratedFromChatId() {
        return this.isFlagSet(FLAG_MIGRATED_FROM_CHAT_ID);
    }
    
    public int getMigratedFromChatId() {
        return migratedFromChatId;
    }

    public void setMigratedFromChatId(int migratedFromChatId) {
        this.migratedFromChatId = migratedFromChatId;
        this.setFlag(FLAG_MIGRATED_FROM_CHAT_ID, this.migratedFromChatId != 0);
    }

    public boolean hasMigratedFromMaxId() {
        return this.isFlagSet(FLAG_MIGRATED_FROM_MAX_ID);
    }
    
    public int getMigratedFromMaxId() {
        return migratedFromMaxId;
    }

    public void setMigratedFromMaxId(int migratedFromMaxId) {
        this.migratedFromMaxId = migratedFromMaxId;
        this.setFlag(FLAG_MIGRATED_FROM_MAX_ID, this.migratedFromMaxId != 0);
    }

    public boolean hasPinnedMsgId() {
        return this.isFlagSet(FLAG_PINNED_MSG_ID);
    }
    
    public int getPinnedMsgId() {
        return pinnedMsgId;
    }

    public void setPinnedMsgId(int pinnedMsgId) {
        this.pinnedMsgId = pinnedMsgId;
        this.setFlag(FLAG_PINNED_MSG_ID, this.pinnedMsgId != 0);
    }

    public boolean hasStickerset() {
        return this.isFlagSet(FLAG_STICKERSET);
    }
    
    public TLStickerSet getStickerset() {
        return stickerset;
    }

    public void setStickerset(TLStickerSet stickerset) {
        this.stickerset = stickerset;
        this.setFlag(FLAG_STICKERSET, this.stickerset != null);
    }

    public boolean hasAvailableMinId() {
        return this.isFlagSet(FLAG_AVAILABLE_MIN_ID);
    }
    
    public int getAvailableMinId() {
        return availableMinId;
    }

    public void setAvailableMinId(int availableMinId) {
        this.availableMinId = availableMinId;
        this.setFlag(FLAG_AVAILABLE_MIN_ID, this.availableMinId != 0);
    }

    public boolean hasFolderId() {
        return this.isFlagSet(FLAG_FOLDER_ID);
    }
    
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
        this.setFlag(FLAG_FOLDER_ID, this.folderId != 0);
    }

    public boolean hasLinkedChatId() {
        return this.isFlagSet(FLAG_LINKED_CHAT_ID);
    }
    
    public int getLinkedChatId() {
        return linkedChatId;
    }

    public void setLinkedChatId(int linkedChatId) {
        this.linkedChatId = linkedChatId;
        this.setFlag(FLAG_LINKED_CHAT_ID, this.linkedChatId != 0);
    }

    public boolean hasLocation() {
        return this.isFlagSet(FLAG_LOCATION);
    }
    
    public TLAbsChannelLocation getLocation() {
        return location;
    }

    public void setLocation(TLAbsChannelLocation location) {
        this.location = location;
        this.setFlag(FLAG_LOCATION, this.location != null);
    }

    public boolean hasSlowmodeSeconds() {
        return this.isFlagSet(FLAG_SLOWMODE_SECONDS);
    }
    
    public int getSlowmodeSeconds() {
        return slowmodeSeconds;
    }

    public void setSlowmodeSeconds(int slowmodeSeconds) {
        this.slowmodeSeconds = slowmodeSeconds;
        this.setFlag(FLAG_SLOWMODE_SECONDS, this.slowmodeSeconds != 0);
    }

    public boolean hasSlowmodeNextSendDate() {
        return this.isFlagSet(FLAG_SLOWMODE_NEXT_SEND_DATE);
    }
    
    public int getSlowmodeNextSendDate() {
        return slowmodeNextSendDate;
    }

    public void setSlowmodeNextSendDate(int slowmodeNextSendDate) {
        this.slowmodeNextSendDate = slowmodeNextSendDate;
        this.setFlag(FLAG_SLOWMODE_NEXT_SEND_DATE, this.slowmodeNextSendDate != 0);
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLString(this.about, stream);
        if (this.hasParticipantsCount()) {
            StreamingUtils.writeInt(this.participantsCount, stream);
        }
        if (this.hasAdminsCount()) {
            StreamingUtils.writeInt(this.adminsCount, stream);
        }
        if (this.hasKickedCount() || this.hasBannedCount()) {
            StreamingUtils.writeInt(this.kickedCount, stream);
            StreamingUtils.writeInt(this.bannedCount, stream);
        }
        if (this.hasOnlineCount()) {
            StreamingUtils.writeInt(this.onlineCount, stream);
        }
        StreamingUtils.writeInt(this.readInboxMaxId, stream);
        StreamingUtils.writeInt(this.readOutboxMaxId, stream);
        StreamingUtils.writeInt(this.unreadCount, stream);
        StreamingUtils.writeTLObject(this.chatPhoto, stream);
        StreamingUtils.writeTLObject(this.notifySettings, stream);
        StreamingUtils.writeTLObject(this.exportedInvite, stream);
        StreamingUtils.writeTLVector(this.botInfo, stream);
        if (this.hasMigratedFromChatId() || this.hasMigratedFromMaxId()) {
            StreamingUtils.writeInt(this.migratedFromChatId, stream);
            StreamingUtils.writeInt(this.migratedFromMaxId, stream);
        }
        if (this.hasPinnedMsgId()) {
            StreamingUtils.writeInt(this.pinnedMsgId, stream);
        }
        if (this.hasStickerset()) {
            StreamingUtils.writeTLObject(this.stickerset, stream);
        }
        if (this.hasAvailableMinId()) {
            StreamingUtils.writeInt(this.availableMinId, stream);
        }
        if (this.hasFolderId()) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
        if (this.hasLinkedChatId()) {
            StreamingUtils.writeInt(this.linkedChatId, stream);
        }
        if (this.hasLocation()) {
            StreamingUtils.writeTLObject(this.location, stream);
        }
        if (this.hasSlowmodeSeconds()) {
            StreamingUtils.writeInt(this.slowmodeSeconds, stream);
        }
        if (this.hasSlowmodeNextSendDate()) {
            StreamingUtils.writeInt(this.slowmodeNextSendDate, stream);
        }
        StreamingUtils.writeInt(this.pts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.about = StreamingUtils.readTLString(stream);
        if (this.hasParticipantsCount()) {
            this.participantsCount = StreamingUtils.readInt(stream);
        }
        if (this.hasAdminsCount()) {
            this.adminsCount = StreamingUtils.readInt(stream);
        }
        if (this.hasKickedCount() || this.hasBannedCount()) {
            this.kickedCount = StreamingUtils.readInt(stream);
            this.bannedCount = StreamingUtils.readInt(stream);
        }
        if (this.hasOnlineCount()) {
            this.onlineCount = StreamingUtils.readInt(stream);
        }
        this.readInboxMaxId = StreamingUtils.readInt(stream);
        this.readOutboxMaxId = StreamingUtils.readInt(stream);
        this.unreadCount = StreamingUtils.readInt(stream);
        this.chatPhoto = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        this.notifySettings = StreamingUtils.readTLObject(stream, context, TLAbsPeerNotifySettings.class);
        this.exportedInvite = StreamingUtils.readTLObject(stream, context, TLAbsExportedChatInvite.class);
        this.botInfo = StreamingUtils.readTLVector(stream, context, TLBotInfo.class);
        if (this.hasMigratedFromChatId() || this.hasMigratedFromMaxId()) {
            this.migratedFromChatId = StreamingUtils.readInt(stream);
            this.migratedFromMaxId = StreamingUtils.readInt(stream);
        }
        if (this.hasPinnedMsgId()) {
            this.pinnedMsgId = StreamingUtils.readInt(stream);
        }
        if (this.hasStickerset()) {
            this.stickerset = StreamingUtils.readTLObject(stream, context, TLStickerSet.class);
        }
        if (this.hasAvailableMinId()) {
            this.availableMinId = StreamingUtils.readInt(stream);
        }
        if (this.hasFolderId()) {
            this.folderId = StreamingUtils.readInt(stream);
        }
        if (this.hasLinkedChatId()) {
            this.linkedChatId = StreamingUtils.readInt(stream);
        }
        if (this.hasLocation()) {
            this.location = StreamingUtils.readTLObject(stream, context, TLAbsChannelLocation.class);
        }
        if (this.hasSlowmodeSeconds()) {
            this.slowmodeSeconds = StreamingUtils.readInt(stream);
        }
        if (this.hasSlowmodeNextSendDate()) {
            this.slowmodeNextSendDate = StreamingUtils.readInt(stream);
        }
        this.pts = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "channelFull#2d895c74";
    }

}