package org.javagram.api.user.base;

import org.javagram.api.user.base.profile.photo.TLAbsUserProfilePhoto;
import org.javagram.api.user.base.status.TLAbsUserStatus;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLVector;

/**
 * User information
 */
public class TLUser extends TLAbsUser {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x938458c1;

    private static final int FLAG_ACCESS_HASH           = 0x00000001; // 00 : Access hash of the user
    private static final int FLAG_FIRST_NAME            = 0x00000002; // 01 : First name
    private static final int FLAG_LAST_NAME             = 0x00000004; // 02 : Last name
    private static final int FLAG_USERNAME              = 0x00000008; // 03 : Username
    private static final int FLAG_PHONE                 = 0x00000010; // 04 : Phone number
    private static final int FLAG_PHOTO                 = 0x00000020; // 05 : Profile picture of user
    private static final int FLAG_STATUS                = 0x00000040; // 06 : Online status of user
    private static final int FLAG_SELF                  = 0x00000400; // 10 : Whether this user indicates the currently logged in user
    private static final int FLAG_CONTACT               = 0x00000800; // 11 : Whether this user is a contact
    private static final int FLAG_MUTUAL_CONTACT        = 0x00001000; // 12 : Whether this user is a mutual contact
    private static final int FLAG_DELETED               = 0x00002000; // 13 : Whether the account of this user was deleted
    private static final int FLAG_BOT                   = 0x00004000; // 14 : Is this user a bot?
    private static final int FLAG_BOT_CHAT_HISTORY      = 0x00008000; // 15 : Can the bot see all messages in groups?
    private static final int FLAG_BOT_NOCHATS           = 0x00010000; // 16 : Can the bot be added to groups?
    private static final int FLAG_VERIFIED              = 0x00020000; // 17 : Whether this user is verified
    private static final int FLAG_RESTRICTED            = 0x00040000; // 18 : Access to this user must be restricted for the reason specified in <code>restriction_reason</code>
    private static final int FLAG_BOT_INLINE_PLACEHOLDER= 0x00080000; // 19 : Inline placeholder for this inline bot
    private static final int FLAG_MIN                   = 0x00100000; // 20 : See @see <a href="https://core.telegram.org/api/min">min</a>
    private static final int FLAG_BOT_INLINE_GEO        = 0x00200000; // 21 : Whether the bot can request our geolocation in inline mode
    private static final int FLAG_LANG_CODE             = 0x00400000; // 22 : Language code of the user
    private static final int FLAG_SUPPORT               = 0x00800000; // 23 : Whether this is an official support user
    private static final int FLAG_SCAM                  = 0x01000000; // 24 : This may be a scam user

    /**
     * First name
     */
    private String firstName = "";

    /**
     * Last name
     */
    private String lastName = "";

    /**
     * Username
     */
    private String userName = "";

    /**
     * Phone number
     */
    private String phone = "";

    /**
     * Profile picture of user
     */
    private TLAbsUserProfilePhoto photo;

    /**
     * Online status of user
     */
    private TLAbsUserStatus status;

    /**
     * Version of the @see <a href="https://core.telegram.org/constructor/userFull">bot_info field in userFull</a>, incremented every time it changes
     */
    private int botInfoVersion;

    /**
     * Contains the reason why access to this user must be restricted.
     */
    private TLVector<String> restrictionReason;

    /**
     * Inline placeholder for this inline bot
     */
    private String botInlinePlaceholder;

    /**
     * Language code of the user
     */
    private String langCode;

    public TLUser() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isSelf() {
        return this.isFlagSet(FLAG_SELF);
    }

    public boolean isContact() {
        return this.isFlagSet(FLAG_CONTACT);
    }

    public boolean isMutualContact() {
        return this.isFlagSet(FLAG_MUTUAL_CONTACT);
    }

    public boolean isDeleted() {
        return this.isFlagSet(FLAG_DELETED);
    }

    @Override
    public boolean isBot() {
        return this.isFlagSet(FLAG_BOT);
    }

    public boolean isBotChatHistory() {
        return this.isFlagSet(FLAG_BOT_CHAT_HISTORY);
    }

    public boolean isBotNoChats() {
        return this.isFlagSet(FLAG_BOT_NOCHATS);
    }

    public boolean isVerified() {
        return this.isFlagSet(FLAG_VERIFIED);
    }

    public boolean isRestricted() {
        return this.isFlagSet(FLAG_RESTRICTED);
    }

    public boolean isMin() {
        return this.isFlagSet(FLAG_MIN);
    }

    public boolean isBotInlineGeo() {
        return this.isFlagSet(FLAG_BOT_INLINE_GEO);
    }

    public boolean isSupport() {
        return this.isFlagSet(FLAG_SUPPORT);
    }

    public boolean isScam() {
        return this.isFlagSet(FLAG_SCAM);
    }

    public boolean hasAccessHash() {
        return this.isFlagSet(FLAG_ACCESS_HASH);
    }

    @Override
    public Long getAccessHash() {
        return this.accessHash;
    }
    
    @Override
    public void setAccessHash(Long accessHash) {
        super.setAccessHash(accessHash);
        this.setFlag(FLAG_ACCESS_HASH, this.accessHash != null);
    }

    public boolean hasFirstName() {
        return this.isFlagSet(FLAG_FIRST_NAME);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.setFlag(FLAG_FIRST_NAME, this.firstName != null && !this.firstName.trim().isEmpty());
    }

    public boolean hasLastName() {
        return this.isFlagSet(FLAG_LAST_NAME);
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.setFlag(FLAG_LAST_NAME, this.lastName != null && !this.lastName.trim().isEmpty());
    }

    public boolean hasUserName() {
        return this.isFlagSet(FLAG_USERNAME);
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        this.setFlag(FLAG_USERNAME, this.userName != null && !this.userName.trim().isEmpty());
    }

    public boolean hasPhone() {
        return this.isFlagSet(FLAG_PHONE);
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        this.setFlag(FLAG_PHONE, this.phone != null && !this.phone.trim().isEmpty());
    }

    public boolean hasPhoto() {
        return this.isFlagSet(FLAG_PHOTO);
    }

    public TLAbsUserProfilePhoto getPhoto() {
        return this.photo;
    }

    public void setPhoto(TLAbsUserProfilePhoto photo) {
        this.photo = photo;
        this.setFlag(FLAG_PHOTO, this.photo != null);
    }

    public boolean hasStatus() {
        return this.isFlagSet(FLAG_STATUS);
    }

    public TLAbsUserStatus getStatus() {
        return this.status;
    }

    public void setStatus(TLAbsUserStatus status) {
        this.status = status;
        this.setFlag(FLAG_STATUS, this.status != null);
    }

    public boolean hasBotInfoVersion() {
        return this.isFlagSet(FLAG_BOT);
    }

    public int getBotInfoVersion() {
        return this.botInfoVersion;
    }

    public void setBotInfoVersion(int botInfoVersion) {
        this.botInfoVersion = botInfoVersion;
        this.setFlag(FLAG_BOT, this.botInfoVersion != 0);
    }

    public boolean hasRestrictionReasons() {
        return this.isFlagSet(FLAG_RESTRICTED);
    }

    public TLVector<String> getRestrictionReason() {
        return restrictionReason;
    }

    public void setRestrictionReason(TLVector<String> restrictionReason) {
        this.restrictionReason = restrictionReason;
        this.setFlag(FLAG_RESTRICTED, this.restrictionReason != null && !this.restrictionReason.isEmpty());
    }

    public boolean hasBotInlinePlaceholder() {
        return this.isFlagSet(FLAG_BOT_INLINE_PLACEHOLDER);
    }

    public String getBotInlinePlaceholder() {
        return botInlinePlaceholder;
    }

    public void setBotInlinePlaceholder(String botInlinePlaceholder) {
        this.botInlinePlaceholder = botInlinePlaceholder;
        this.setFlag(FLAG_BOT_INLINE_PLACEHOLDER, this.botInlinePlaceholder != null && !this.botInlinePlaceholder.trim().isEmpty());
    }

    public boolean hasLangCode() {
        return this.isFlagSet(FLAG_LANG_CODE);
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
        this.setFlag(FLAG_LANG_CODE, this.langCode != null && !this.langCode.trim().isEmpty());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);

        if (this.hasAccessHash()) {
            StreamingUtils.writeLong(this.accessHash, stream);
        }
        if (this.hasFirstName()) {
            StreamingUtils.writeTLString(this.firstName, stream);
        }
        if (this.hasLastName()) {
            StreamingUtils.writeTLString(this.lastName, stream);
        }
        if (this.hasUserName()) {
            StreamingUtils.writeTLString(this.userName, stream);
        }
        if (this.hasPhone()) {
            StreamingUtils.writeTLString(this.phone, stream);
        }
        if (this.hasPhoto()) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        if (this.hasStatus()) {
            StreamingUtils.writeTLObject(this.status, stream);
        }
        if (this.hasBotInfoVersion()) {
            StreamingUtils.writeInt(this.botInfoVersion, stream);
        }
        if (this.hasRestrictionReasons()) {
            StreamingUtils.writeTLVector(this.restrictionReason, stream);
        }
        if (this.hasBotInlinePlaceholder()) {
            StreamingUtils.writeTLString(this.botInlinePlaceholder, stream);
        }
        if (this.hasLangCode()) {
            StreamingUtils.writeTLString(this.langCode, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);

        if (this.hasAccessHash()) {
            this.setAccessHash(StreamingUtils.readLong(stream));
        }
        if (this.hasFirstName()) {
            this.firstName = StreamingUtils.readTLString(stream);
        }
        if (this.hasLastName()) {
            this.lastName = StreamingUtils.readTLString(stream);
        }
        if (this.hasUserName()) {
            this.userName = StreamingUtils.readTLString(stream);
        }
        if (this.hasPhone()) {
            this.phone = StreamingUtils.readTLString(stream);
        }
        if (this.hasPhoto()) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLAbsUserProfilePhoto.class);
        }
        if (this.hasStatus()) {
            this.status = StreamingUtils.readTLObject(stream, context, TLAbsUserStatus.class);
        }
        if (this.hasBotInfoVersion()) {
            this.botInfoVersion = StreamingUtils.readInt(stream);
        }
        if (this.hasRestrictionReasons()) {
            this.restrictionReason = StreamingUtils.readTLVector(stream, context, String.class);
        }
        if (this.hasBotInlinePlaceholder()) {
            this.botInlinePlaceholder = StreamingUtils.readTLString(stream);
        }
        if (this.hasLangCode()) {
            this.langCode = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "user#938458c1";
    }

}