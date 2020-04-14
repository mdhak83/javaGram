package org.javagram.api.chat.base;

import org.javagram.api.chat.base.photo.TLAbsChatPhoto;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.photo.TLChatPhoto;
import org.javagram.api._primitives.TLVector;

/**
 * channel
 * Channel/supergroup info
 * channel#d31a961e flags:# creator:flags.0?true left:flags.2?true broadcast:flags.5?true verified:flags.7?true megagroup:flags.8?true restricted:flags.9?true signatures:flags.11?true min:flags.12?true scam:flags.19?true has_link:flags.20?true has_geo:flags.21?true slowmode_enabled:flags.22?true id:int access_hash:flags.13?long title:string username:flags.6?string photo:ChatPhoto date:int version:int restriction_reason:flags.9?Vector&lt;RestrictionReason&gt; admin_rights:flags.14?ChatAdminRights banned_rights:flags.15?ChatBannedRights default_banned_rights:flags.18?ChatBannedRights participants_count:flags.17?int = Chat;
 */
public class TLChannel extends TLAbsChat {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd31a961e;

    private static final int FLAG_CREATOR               = 0x00000001; // 00 : Whether the current user is the creator of this channel
    private static final int FLAG_LEFT                  = 0x00000004; // 02 : Whether the current user has left this channel
    private static final int FLAG_BROADCAST             = 0x00000020; // 05 : Is this a channel?
    private static final int FLAG_USERNAME              = 0x00000040; // 06 : Username
    private static final int FLAG_VERIFIED              = 0x00000080; // 07 : Is this channel verified by telegram?
    private static final int FLAG_MEGAGROUP             = 0x00000100; // 08 : Is this a supergroup?
    private static final int FLAG_RESTRICTED            = 0x00000200; // 09 : Whether viewing/writing in this channel for a reason (see <code>restriction_reason</code>)
    private static final int FLAG_SIGNATURES            = 0x00000800; // 11 : Whether signatures are enabled (channels)
    private static final int FLAG_MIN                   = 0x00001000; // 12 : See @see <a href="https://core.telegram.org/api/min">min</a>
    private static final int FLAG_ACCESS_HASH           = 0x00002000; // 13 : Access hash
    private static final int FLAG_ADMIN_RIGHTS          = 0x00004000; // 14 : Admin rights of the user in this channel (see @see <a href="https://core.telegram.org/api/rights">rights</a>)
    private static final int FLAG_BANNED_RIGHTS         = 0x00008000; // 15 : Banned rights of the user in this channel (see @see <a href="https://core.telegram.org/api/rights">rights</a>)
    private static final int FLAG_PARTICIPANTS_COUNT    = 0x00020000; // 17 : Participant count
    private static final int FLAG_DEFAULT_BANNED_RIGHTS = 0x00040000; // 18 : Default chat rights (see @see <a href="https://core.telegram.org/api/rights">rights</a>)
    private static final int FLAG_SCAM                  = 0x00080000; // 19 : This channel/supergroup is probably a scam
    private static final int FLAG_HAS_LINK              = 0x00100000; // 20 : Whether this channel has a private join link
    private static final int FLAG_HAS_GEO               = 0x00200000; // 21 : Whether this chanel has a geoposition
    private static final int FLAG_SLOWMODE_ENABLED      = 0x00400000; // 22 : Whether slow mode is enabled for groups to prevent flood in chat

    /**
     * Title
     */
    private String title;

    /**
     * Username
     */
    private String username;

    /**
     * Profile photo
     */
    private TLAbsChatPhoto photo;

    /**
     * Creation date
     */
    private int date;

    /**
     * Version of the channel (always <code>0</code>)
     */
    private int version;

    /**
     * Contains the reason why access to this channel must be restricted.
     */
    private TLVector<String> restrictionReason;

    /**
     * Admin rights of the user in this channel (see @see <a href="https://core.telegram.org/api/rights">rights</a>)
     */
    private TLChatAdminRights adminRights;

    /**
     * Banned rights of the user in this channel (see @see <a href="https://core.telegram.org/api/rights">rights</a>)
     */
    private TLChatBannedRights bannedRights;

    /**
     * Default chat rights (see @see <a href="https://core.telegram.org/api/rights">rights</a>)
     */
    private TLChatBannedRights defaultBannedRights;

    /**
     * Participant count
     */
    private int participantsCount;

    public TLChannel() {
        super();
    }
    
    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public boolean isChannel() {
        return true;
    }

    public boolean isCreator() {
        return this.isFlagSet(FLAG_CREATOR);
    }
    
    public void setCreator(boolean value) {
        this.setFlag(FLAG_CREATOR, value);
    }

    public boolean isLeft() {
        return this.isFlagSet(FLAG_LEFT);
    }
    
    public void setLeft(boolean value) {
        this.setFlag(FLAG_LEFT, value);
    }

    public boolean isBroadcast() {
        return this.isFlagSet(FLAG_BROADCAST);
    }
    
    public void setBroadcast(boolean value) {
        this.setFlag(FLAG_BROADCAST, value);
    }

    public boolean isVerified() {
        return this.isFlagSet(FLAG_VERIFIED);
    }
    
    public void setVerified(boolean value) {
        this.setFlag(FLAG_VERIFIED, value);
    }

    public boolean isMegagroup() {
        return this.isFlagSet(FLAG_MEGAGROUP);
    }
    
    public void setMegagroup(boolean value) {
        this.setFlag(FLAG_MEGAGROUP, value);
    }

    public boolean isRestricted() {
        return this.isFlagSet(FLAG_RESTRICTED);
    }
    
    public void setRestricted(boolean value) {
        this.setFlag(FLAG_RESTRICTED, value);
    }

    public boolean isSignatures() {
        return this.isFlagSet(FLAG_SIGNATURES);
    }
    
    public void setSignatures(boolean value) {
        this.setFlag(FLAG_SIGNATURES, value);
    }

    @Override
    public boolean isMin() {
        return this.isFlagSet(FLAG_MIN);
    }
    
    public void setMin(boolean value) {
        this.setFlag(FLAG_MIN, value);
    }

    public boolean isScam() {
        return this.isFlagSet(FLAG_SCAM);
    }
    
    public void setScam(boolean value) {
        this.setFlag(FLAG_SCAM, value);
    }

    public boolean isHasLink() {
        return this.isFlagSet(FLAG_HAS_LINK);
    }
    
    public void setHasLink(boolean value) {
        this.setFlag(FLAG_HAS_LINK, value);
    }

    public boolean isHasGeo() {
        return this.isFlagSet(FLAG_HAS_GEO);
    }
    
    public void setHasGeo(boolean value) {
        this.setFlag(FLAG_HAS_GEO, value);
    }

    public boolean isSlowmodeEnabled() {
        return this.isFlagSet(FLAG_SLOWMODE_ENABLED);
    }
    
    public void setSlowmodeEnabled(boolean value) {
        this.setFlag(FLAG_SLOWMODE_ENABLED, value);
    }

    public boolean hasAccessHash() {
        return this.isFlagSet(FLAG_ACCESS_HASH);
    }

    @Override
    public Long getAccessHash() {
        return accessHash;
    }

    @Override
    public void setAccessHash(Long accessHash) {
        super.setAccessHash(accessHash);
        this.setFlag(FLAG_ACCESS_HASH, accessHash != null);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasUsername() {
        return this.isFlagSet(FLAG_USERNAME);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public TLChatPhoto getLogo() {
        TLChatPhoto ret = null;
        if (this.photo != null && this.photo instanceof TLChatPhoto) {
            ret = (TLChatPhoto) this.photo;
        }
        return ret;
    }
    
    public TLAbsChatPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(TLAbsChatPhoto photo) {
        this.photo = photo;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean hasRestrictionReason() {
        return this.isFlagSet(FLAG_RESTRICTED);
    }

    public TLVector<String> getRestrictionReason() {
        return restrictionReason;
    }

    public void setRestrictionReason(TLVector<String> restrictionReason) {
        this.restrictionReason = restrictionReason;
        this.setFlag(FLAG_RESTRICTED, this.restrictionReason != null && !this.restrictionReason.isEmpty());
    }

    public boolean hasAdminRights() {
        return this.isFlagSet(FLAG_ADMIN_RIGHTS);
    }

    public TLChatAdminRights getAdminRights() {
        return adminRights;
    }

    public void setAdminRights(TLChatAdminRights adminRights) {
        this.adminRights = adminRights;
        this.setFlag(FLAG_ADMIN_RIGHTS, this.defaultBannedRights != null);
    }

    public boolean hasBannedRights() {
        return this.isFlagSet(FLAG_BANNED_RIGHTS);
    }

    public TLChatBannedRights getBannedRights() {
        return bannedRights;
    }

    public void setBannedRights(TLChatBannedRights bannedRights) {
        this.bannedRights = bannedRights;
        this.setFlag(FLAG_BANNED_RIGHTS, this.defaultBannedRights != null);
    }

    public boolean hasDefaultBannedRights() {
        return this.isFlagSet(FLAG_DEFAULT_BANNED_RIGHTS);
    }

    public TLChatBannedRights getDefaultBannedRights() {
        return defaultBannedRights;
    }

    public void setDefaultBannedRights(TLChatBannedRights defaultBannedRights) {
        this.defaultBannedRights = defaultBannedRights;
        this.setFlag(FLAG_DEFAULT_BANNED_RIGHTS, this.defaultBannedRights != null);
    }

    public boolean hasParticipantsCount() {
        return this.isFlagSet(FLAG_PARTICIPANTS_COUNT);
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
        this.setFlag(FLAG_PARTICIPANTS_COUNT, true);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        if (this.hasAccessHash()) {
            StreamingUtils.writeLong(this.accessHash, stream);
        }
        StreamingUtils.writeTLString(this.title, stream);
        if (this.hasUsername()) {
            StreamingUtils.writeTLString(this.username, stream);
        }
        StreamingUtils.writeTLObject(this.photo, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.version, stream);
        if (this.hasRestrictionReason()) {
            StreamingUtils.writeTLVector(this.restrictionReason, stream);
        }
        if (this.hasAdminRights()) {
            StreamingUtils.writeTLObject(this.adminRights, stream);
        }
        if (this.hasBannedRights()) {
            StreamingUtils.writeTLObject(this.bannedRights, stream);
        }
        if (this.hasDefaultBannedRights()) {
            StreamingUtils.writeTLObject(this.defaultBannedRights, stream);
        }
        if (this.hasParticipantsCount()) {
            StreamingUtils.writeInt(this.participantsCount, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        if (this.hasAccessHash()) {
            this.setAccessHash(StreamingUtils.readLong(stream));
        }
        this.title = StreamingUtils.readTLString(stream);
        if (this.hasUsername()) {
            this.username = StreamingUtils.readTLString(stream);
        }
        this.photo = StreamingUtils.readTLObject(stream, context, TLAbsChatPhoto.class);
        this.date = StreamingUtils.readInt(stream);
        this.version = StreamingUtils.readInt(stream);
        if (this.hasRestrictionReason()) {
            this.restrictionReason = StreamingUtils.readTLVector(stream, context, String.class);
        }
        if (this.hasAdminRights()) {
            this.adminRights = StreamingUtils.readTLObject(stream, context, TLChatAdminRights.class);
        }
        if (this.hasBannedRights()) {
            this.bannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
        }
        if (this.hasDefaultBannedRights()) {
            this.defaultBannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
        }
        if (this.hasParticipantsCount()) {
            this.participantsCount = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "channel#d31a961e";
    }

    @Override
    public String toLog() {
        return ("Channel: " + this.title + " (id=" + String.format("%08x", this.id) + ";accessHash=" + String.format("%016x", this.accessHash) + ")");
    }
    
}