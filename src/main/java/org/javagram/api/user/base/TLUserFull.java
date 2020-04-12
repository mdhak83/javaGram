package org.javagram.api.user.base;

import org.javagram.api.bot.base.TLBotInfo;
import org.javagram.api.peer.base.notify.settings.TLAbsPeerNotifySettings;
import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.TLPeerSettings;

/**
 * Extended user info
 */
public class TLUserFull extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedf17c12;

    private static final int FLAG_BLOCKED               = 0x00000001; // 0  : Whether you have blocked this user
    private static final int FLAG_ABOUT                 = 0x00000002; // 1  : Bio of the user
    private static final int FLAG_PROFILE_PHOTO         = 0x00000004; // 2  : Profile photo
    private static final int FLAG_BOT_INFO              = 0x00000008; // 3  : For bots, info about the bot (bot commands, etc)
    private static final int FLAG_PHONE_CALLS_AVAILABLE = 0x00000010; // 4  : Whether this user can make VoIP calls
    private static final int FLAG_PHONE_CALLS_PRIVATE   = 0x00000020; // 5  : Whether this user's privacy settings allow you to call him
    private static final int FLAG_PINNED_MSG_ID         = 0x00000040; // 6  : Pinned message ID, you can only pin messages in a chat with yourself
    private static final int FLAG_CAN_PIN_MESSAGE       = 0x00000080; // 7  : Whether you can pin messages in the chat with this user, you can do this only for a chat with yourself
    private static final int FLAG_FOLDER_ID             = 0x00000800; // 11 : Folder ID
    private static final int FLAG_HAS_SCHEDULED         = 0x00001000; // 12 : Whether scheduled messages are available

    /**
     * Remaining user info
     */
    private TLAbsUser user;
    
    /**
     * Bio of the user
     */
    private String about;
    
    /**
     * Peer settings
     */
    private TLPeerSettings settings;
    
    /**
     * Profile photo
     */
    private TLAbsPhoto profilePhoto;
    
    /**
     * Notification settings
     */
    private TLAbsPeerNotifySettings notifySettings;
    
    /**
     * For bots, info about the bot (bot commands, etc)
     */
    private TLBotInfo botInfo;
    
    /**
     * Pinned message ID, you can only pin messages in a chat with yourself
     */
    private int pinnedMsgId;
    
    /**
     * Chats in common with this user
     */
    private int commonChatsCount;
    
    /**
     * Folder ID
     */
    private int folderId;

    public TLUserFull() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isBlocked() {
        return (flags & FLAG_BLOCKED) != 0;
    }

    public boolean hasPhoneCallsAvailable() {
        return (flags & FLAG_PHONE_CALLS_AVAILABLE) != 0;
    }

    public boolean hasPhoneCallsPrivate() {
        return (flags & FLAG_PHONE_CALLS_PRIVATE) != 0;
    }

    public boolean canPinMessage() {
        return (flags & FLAG_CAN_PIN_MESSAGE) != 0;
    }

    public boolean hasScheduled() {
        return (flags & FLAG_HAS_SCHEDULED) != 0;
    }

    public TLAbsUser getUser() {
        return user;
    }

    public void setUser(TLAbsUser user) {
        this.user = user;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public TLPeerSettings getSettings() {
        return settings;
    }

    public void setSettings(TLPeerSettings settings) {
        this.settings = settings;
    }

    public TLAbsPhoto getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(TLAbsPhoto profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public TLAbsPeerNotifySettings getNotifySettings() {
        return notifySettings;
    }

    public void setNotifySettings(TLAbsPeerNotifySettings notifySettings) {
        this.notifySettings = notifySettings;
    }

    public TLBotInfo getBotInfo() {
        return botInfo;
    }

    public void setBotInfo(TLBotInfo botInfo) {
        this.botInfo = botInfo;
    }

    public int getPinnedMsgId() {
        return pinnedMsgId;
    }

    public void setPinnedMsgId(int pinnedMsgId) {
        this.pinnedMsgId = pinnedMsgId;
    }

    public int getCommonChatsCount() {
        return commonChatsCount;
    }

    public void setCommonChatsCount(int commonChatsCount) {
        this.commonChatsCount = commonChatsCount;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.user, stream);
        if ((this.flags & FLAG_ABOUT) != 0) {
            StreamingUtils.writeTLString(this.about, stream);
        }
        StreamingUtils.writeTLObject(this.settings, stream);
        if ((this.flags & FLAG_PROFILE_PHOTO) != 0) {
            StreamingUtils.writeTLObject(this.profilePhoto, stream);
        }
        StreamingUtils.writeTLObject(this.notifySettings, stream);
        if ((this.flags & FLAG_BOT_INFO) != 0) {
            StreamingUtils.writeTLObject(this.botInfo, stream);
        }
        if ((this.flags & FLAG_PINNED_MSG_ID) != 0) {
            StreamingUtils.writeInt(this.pinnedMsgId, stream);
        }
        StreamingUtils.writeInt(this.commonChatsCount, stream);
        if ((this.flags & FLAG_FOLDER_ID) != 0) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.user = StreamingUtils.readTLObject(stream, context, TLAbsUser.class);
        if ((this.flags & FLAG_ABOUT) != 0) {
            this.about = StreamingUtils.readTLString(stream);
        }
        this.settings = StreamingUtils.readTLObject(stream, context, TLPeerSettings.class);
        if ((this.flags & FLAG_PROFILE_PHOTO) != 0) {
            this.profilePhoto = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        }
        this.notifySettings = StreamingUtils.readTLObject(stream, context, TLAbsPeerNotifySettings.class);
        if ((this.flags & FLAG_BOT_INFO) != 0) {
            this.botInfo = StreamingUtils.readTLObject(stream, context, TLBotInfo.class);
        }
        if ((this.flags & FLAG_PINNED_MSG_ID) != 0) {
            this.pinnedMsgId = StreamingUtils.readInt(stream);
        }
        this.commonChatsCount = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FOLDER_ID) != 0) {
            this.folderId = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "userFull#edf17c12";
    }

}