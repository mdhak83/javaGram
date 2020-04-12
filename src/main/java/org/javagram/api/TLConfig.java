package org.javagram.api;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Current configuration.
 */
public class TLConfig extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x330b4067;

    private static final int FLAG_TMP_SESSIONS              = 0x00000001; // 0  : Temporary @see <a href="https://core.telegram.org/passport">passport</a> sessions
    private static final int FLAG_PHONE_CALLS_ENABLED       = 0x00000002; // 1  : Whether phone calls can be used
    private static final int FLAG_LANG                      = 0x00000004; // 2  : Include language variables
    private static final int FLAG_DEFAULT_P2P_CONTACTS      = 0x00000008; // 3  : Whether the client should use P2P by default for phone calls with contacts
    private static final int FLAG_PRELOAD_FEATURED_STICKERS = 0x00000010; // 4  : Whether the client should preload featured stickers
    private static final int FLAG_IGNORE_PHONE_ENTITIES     = 0x00000020; // 5  : Whether the client should ignore phone entities
    private static final int FLAG_REVOKE_PM_INBOX           = 0x00000040; // 6  : Whether incoming private messages can be deleted for both participants
    private static final int FLAG_AUTOUPDATE_URL_PREFIX     = 0x00000080; // 7  : URL to use to auto-update the current app
    private static final int FLAG_BLOCKED_MODE              = 0x00000100; // 8  : Indicates that telegram is probably censored by governments/ISPs in the current region
    private static final int FLAG_GIF_SEARCH_USERNAME       = 0x00000200; // 9  : Username of the bot to use to search for GIFs
    private static final int FLAG_VENUE_SEARCH_USERNAME     = 0x00000400; // 10 : Username of the bot to use to search for venues
    private static final int FLAG_IMG_SEARCH_USERNAME       = 0x00000800; // 11 : Username of the bot to use for image search
    private static final int FLAG_STATIC_MAPS_PROVIDER      = 0x00001000; // 12 : ID of the map provider to use for venues
    private static final int FLAG_PFS_ENABLED               = 0x00002000; // 13 : Whether @see <a href="https://core.telegram.org/api/pfs">pfs</a> was used

    /**
     * Current date at the server
     */
    private int date;
    
    /**
     * Expiration date of this config: when it expires it'll have to be refetched using @see <a href="https://core.telegram.org/method/help.getConfig">help.getConfig</a>
     */
    private int expires;
    
    /**
     * Whether we're connected to the test DCs
     */
    private boolean testMode;
    
    /**
     * ID of the DC that returned the reply
     */
    private int thisDc;
    
    /**
     * DC IP list
     */
    private TLVector<TLDcOption> dcOptions;
    
    /**
     * Domain name for fetching encrypted DC list from DNS TXT record
     */
    private String dcTxtDomainName;
    
    /**
     * Maximum member count for normal @see <a href="https://core.telegram.org/api/channel">groups</a>
     */
    private int chatSizeMax;
    
    /**
     * Maximum member count for @see <a href="https://core.telegram.org/api/channel">supergroups</a>
     */
    private int megaGroupSizeMax;
    
    /**
     * Maximum number of messages that can be forwarded at once using @see <a href="https://core.telegram.org/method/messages.forwardMessages">messages.forwardMessages</a>.
     */
    private int forwardedCountMax;
    
    /**
     * The client should update its @see <a href="https://core.telegram.org/method/account.updateStatus">online status every N milliseconds</a>
     */
    private int onlineUpdatePeriodMs;
    
    /**
     * Delay before offline status needs to be sent to the server
     */
    private int offlineBlurTimeoutMs;
    
    /**
     * Time without any user activity after which it should be treated offline
     */
    private int offlineIdleTimeoutMs;
    
    /**
     * If we are offline, but were online from some other client in last online_cloud_timeout_ms milliseconds after we had gone offline, then delay offline notification for notify_cloud_delay_ms milliseconds.
     */
    private int onlineCloudTimeoutMs;
    
    /**
     * If we are offline, but online from some other client then delay sending the offline notification for notify_cloud_delay_ms milliseconds.
     */
    private int notifyCloudDelayMs;
    
    /**
     * If some other client is online, then delay notification for notification_default_delay_ms milliseconds
     */
    private int notifyDefaultDelayMs;
    
    /**
     * Not for client use
     */
    private int pushChatPeriodMs;
    
    /**
     * Not for client use
     */
    private int pushChatLimit;
    
    /**
     * Maximum count of saved gifs
     */
    private int savedGifsLimit;

    /**
     * Only messages with age smaller than the one specified can be edited
     */
    private int editTimeLimit;
    
    /**
     * Only channel/supergroup messages with age smaller than the specified can be deleted
     */
    private int revokeTimeLimit;
    
    /**
     * Only private messages with age smaller than the specified can be deleted
     */
    private int revokePmTimeLimit;
    
    /**
     * Exponential decay rate for computing @see <a href="https://core.telegram.org/api/top-rating">top peer rating</a>
     */
    private int ratingEDecay;
    
    /**
     * Maximum number of recent stickers
     */
    private int stickersRecentLimit;
    
    /**
     * Maximum number of faved stickers
     */
    private int stickersFavedLimit;
    
    /**
     * Indicates that round videos (video notes) and voice messages sent in channels and older than the specified period must be marked as read
     */
    private int channelsReadMediaPeriod;
    
    /**
     * Temporary @see <a href="https://core.telegram.org/passport">passport</a> sessions
     */
    private int tmpSessions;
    
    /**
     * Maximum count of pinned dialogs
     */
    private int pinnedDialogsCountMax;
    
    /**
     * Maximum count of dialogs per folder
     */
    private int pinnedInfolderCountMax;
    
    /**
     * Maximum allowed outgoing ring time in VoIP calls: if the user we're calling doesn't reply within the specified time (in milliseconds), we should hang up the call
     */
    private int callReceiveTimeoutMs;
    
    /**
     * Maximum allowed incoming ring time in VoIP calls: if the current user doesn't reply within the specified time (in milliseconds), the call will be automatically refused
     */
    private int callRingTimeoutMs;
    
    /**
     * VoIP connection timeout: if the instance of libtgvoip on the other side of the call doesn't connect to our instance of libtgvoip within the specified time (in milliseconds), the call must be aborted
     */
    private int callConnectTimeoutMs;
    
    /**
     * If during a VoIP call a packet isn't received for the specified period of time, the call must be aborted
     */
    private int callPacketTimeoutMs;
    
    /**
     * The domain to use to parse in-app links.
     * For example t.me indicates that t.me/username links should parsed to @username, t.me/addsticker/name should be parsed to the appropriate stickerset and so on...
     */
    private String meUrlPrefix;
    
    /**
     * URL to use to auto-update the current app
     */
    private String autoupdateUrlPrefix;
    
    /**
     * Username of the bot to use to search for GIFs
     */
    private String gifSearchUsername;
    
    /**
     * Username of the bot to use to search for venues
     */
    private String venueSearchUsername;
    
    /**
     * Username of the bot to use for image search
     */
    private String imgSearchUsername;
    
    /**
     * ID of the map provider to use for venues
     */
    private String staticMapsProvider;

    /**
     * Maximum length of caption (length in utf8 codepoints)
     */
    private int captionLengthMax;
    
    /**
     * Maximum length of messages (length in utf8 codepoints)
     */
    private int messageLengthMax;
    
    /**
     * DC ID to use to download @see <a href="https://core.telegram.org/api/files">webfiles</a>
     */
    private int webfileDcId;
    
    /**
     * Suggested language code
     */
    private String suggestedLangCode;
    
    /**
     * Language pack version
     */
    private int langPackVersion;
    
    /**
     * Basic language pack version
     */
    private int baseLangPackVersion;

    public TLConfig() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isAutoupdateUrlPrefix() {
        return this.isFlagSet(FLAG_AUTOUPDATE_URL_PREFIX);
    }
    
    public boolean hasPhonecallsEnabled() {
        return this.isFlagSet(FLAG_PHONE_CALLS_ENABLED);
    }
    
    public boolean isDefaultP2PContacts() {
        return this.isFlagSet(FLAG_DEFAULT_P2P_CONTACTS);
    }
    
    public boolean preloadFeaturedStickers() {
        return this.isFlagSet(FLAG_PRELOAD_FEATURED_STICKERS);
    }
    
    public boolean isIgnorePhoneEntities() {
        return this.isFlagSet(FLAG_IGNORE_PHONE_ENTITIES);
    }
    
    public boolean isRevokePmInbox() {
        return this.isFlagSet(FLAG_REVOKE_PM_INBOX);
    }
    
    public boolean isBlockedMode() {
        return this.isFlagSet(FLAG_BLOCKED_MODE);
    }
    
    public boolean isPfsEnabled() {
        return this.isFlagSet(FLAG_PFS_ENABLED);
    }
    
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public int getThisDc() {
        return thisDc;
    }

    public void setThisDc(int thisDc) {
        this.thisDc = thisDc;
    }

    public TLVector<TLDcOption> getDcOptions() {
        return dcOptions;
    }

    public void setDcOptions(TLVector<TLDcOption> dcOptions) {
        this.dcOptions = dcOptions;
    }

    public String getDcTxtDomainName() {
        return dcTxtDomainName;
    }

    public void setDcTxtDomainName(String dcTxtDomainName) {
        this.dcTxtDomainName = dcTxtDomainName;
    }

    public int getChatSizeMax() {
        return chatSizeMax;
    }

    public void setChatSizeMax(int chatSizeMax) {
        this.chatSizeMax = chatSizeMax;
    }

    public int getMegaGroupSizeMax() {
        return megaGroupSizeMax;
    }

    public void setMegaGroupSizeMax(int megaGroupSizeMax) {
        this.megaGroupSizeMax = megaGroupSizeMax;
    }

    public int getForwardedCountMax() {
        return forwardedCountMax;
    }

    public void setForwardedCountMax(int forwardedCountMax) {
        this.forwardedCountMax = forwardedCountMax;
    }

    public int getOnlineUpdatePeriodMs() {
        return onlineUpdatePeriodMs;
    }

    public void setOnlineUpdatePeriodMs(int onlineUpdatePeriodMs) {
        this.onlineUpdatePeriodMs = onlineUpdatePeriodMs;
    }

    public int getOfflineBlurTimeoutMs() {
        return offlineBlurTimeoutMs;
    }

    public void setOfflineBlurTimeoutMs(int offlineBlurTimeoutMs) {
        this.offlineBlurTimeoutMs = offlineBlurTimeoutMs;
    }

    public int getOfflineIdleTimeoutMs() {
        return offlineIdleTimeoutMs;
    }

    public void setOfflineIdleTimeoutMs(int offlineIdleTimeoutMs) {
        this.offlineIdleTimeoutMs = offlineIdleTimeoutMs;
    }

    public int getOnlineCloudTimeoutMs() {
        return onlineCloudTimeoutMs;
    }

    public void setOnlineCloudTimeoutMs(int onlineCloudTimeoutMs) {
        this.onlineCloudTimeoutMs = onlineCloudTimeoutMs;
    }

    public int getNotifyCloudDelayMs() {
        return notifyCloudDelayMs;
    }

    public void setNotifyCloudDelayMs(int notifyCloudDelayMs) {
        this.notifyCloudDelayMs = notifyCloudDelayMs;
    }

    public int getNotifyDefaultDelayMs() {
        return notifyDefaultDelayMs;
    }

    public void setNotifyDefaultDelayMs(int notifyDefaultDelayMs) {
        this.notifyDefaultDelayMs = notifyDefaultDelayMs;
    }

    public int getPushChatPeriodMs() {
        return pushChatPeriodMs;
    }

    public void setPushChatPeriodMs(int pushChatPeriodMs) {
        this.pushChatPeriodMs = pushChatPeriodMs;
    }

    public int getPushChatLimit() {
        return pushChatLimit;
    }

    public void setPushChatLimit(int pushChatLimit) {
        this.pushChatLimit = pushChatLimit;
    }

    public int getSavedGifsLimit() {
        return savedGifsLimit;
    }

    public void setSavedGifsLimit(int savedGifsLimit) {
        this.savedGifsLimit = savedGifsLimit;
    }

    public int getEditTimeLimit() {
        return editTimeLimit;
    }

    public void setEditTimeLimit(int editTimeLimit) {
        this.editTimeLimit = editTimeLimit;
    }

    public int getRevokeTimeLimit() {
        return revokeTimeLimit;
    }

    public void setRevokeTimeLimit(int revokeTimeLimit) {
        this.revokeTimeLimit = revokeTimeLimit;
    }

    public int getRevokePmTimeLimit() {
        return revokePmTimeLimit;
    }

    public void setRevokePmTimeLimit(int revokePmTimeLimit) {
        this.revokePmTimeLimit = revokePmTimeLimit;
    }

    public int getRatingEDecay() {
        return ratingEDecay;
    }

    public void setRatingEDecay(int ratingEDecay) {
        this.ratingEDecay = ratingEDecay;
    }

    public int getStickersRecentLimit() {
        return stickersRecentLimit;
    }

    public void setStickersRecentLimit(int stickersRecentLimit) {
        this.stickersRecentLimit = stickersRecentLimit;
    }

    public int getStickersFavedLimit() {
        return stickersFavedLimit;
    }

    public void setStickersFavedLimit(int stickersFavedLimit) {
        this.stickersFavedLimit = stickersFavedLimit;
    }

    public int getChannelsReadMediaPeriod() {
        return channelsReadMediaPeriod;
    }

    public void setChannelsReadMediaPeriod(int channelsReadMediaPeriod) {
        this.channelsReadMediaPeriod = channelsReadMediaPeriod;
    }

    public boolean hasTmpSessions() {
        return this.isFlagSet(FLAG_TMP_SESSIONS);
    }
    
    public int getTmpSessions() {
        return tmpSessions;
    }

    public void setTmpSessions(int tmpSessions) {
        this.tmpSessions = tmpSessions;
        this.setFlag(FLAG_TMP_SESSIONS, true);
    }

    public int getPinnedDialogsCountMax() {
        return pinnedDialogsCountMax;
    }

    public void setPinnedDialogsCountMax(int pinnedDialogsCountMax) {
        this.pinnedDialogsCountMax = pinnedDialogsCountMax;
    }

    public int getPinnedInfolderCountMax() {
        return pinnedInfolderCountMax;
    }

    public void setPinnedInfolderCountMax(int pinnedInfolderCountMax) {
        this.pinnedInfolderCountMax = pinnedInfolderCountMax;
    }

    public int getCallReceiveTimeoutMs() {
        return callReceiveTimeoutMs;
    }

    public void setCallReceiveTimeoutMs(int callReceiveTimeoutMs) {
        this.callReceiveTimeoutMs = callReceiveTimeoutMs;
    }

    public int getCallRingTimeoutMs() {
        return callRingTimeoutMs;
    }

    public void setCallRingTimeoutMs(int callRingTimeoutMs) {
        this.callRingTimeoutMs = callRingTimeoutMs;
    }

    public int getCallConnectTimeoutMs() {
        return callConnectTimeoutMs;
    }

    public void setCallConnectTimeoutMs(int callConnectTimeoutMs) {
        this.callConnectTimeoutMs = callConnectTimeoutMs;
    }

    public int getCallPacketTimeoutMs() {
        return callPacketTimeoutMs;
    }

    public void setCallPacketTimeoutMs(int callPacketTimeoutMs) {
        this.callPacketTimeoutMs = callPacketTimeoutMs;
    }

    public String getMeUrlPrefix() {
        return meUrlPrefix;
    }

    public void setMeUrlPrefix(String meUrlPrefix) {
        this.meUrlPrefix = meUrlPrefix;
    }

    public String getAutoupdateUrlPrefix() {
        return autoupdateUrlPrefix;
    }

    public void setAutoupdateUrlPrefix(String autoupdateUrlPrefix) {
        this.autoupdateUrlPrefix = autoupdateUrlPrefix;
    }

    public boolean hasGifSearchUsername() {
        return this.isFlagSet(FLAG_GIF_SEARCH_USERNAME);
    }
    
    public String getGifSearchUsername() {
        return gifSearchUsername;
    }

    public void setGifSearchUsername(String gifSearchUsername) {
        this.gifSearchUsername = gifSearchUsername;
        this.setFlag(FLAG_GIF_SEARCH_USERNAME, this.gifSearchUsername != null && !this.gifSearchUsername.trim().isEmpty());
    }

    public boolean hasVenueSearchUsername() {
        return this.isFlagSet(FLAG_VENUE_SEARCH_USERNAME);
    }
    
    public String getVenueSearchUsername() {
        return venueSearchUsername;
    }

    public void setVenueSearchUsername(String venueSearchUsername) {
        this.venueSearchUsername = venueSearchUsername;
        this.setFlag(FLAG_VENUE_SEARCH_USERNAME, this.venueSearchUsername != null && !this.venueSearchUsername.trim().isEmpty());
    }

    public boolean hasImgSearchUsername() {
        return this.isFlagSet(FLAG_IMG_SEARCH_USERNAME);
    }
    
    public String getImgSearchUsername() {
        return imgSearchUsername;
    }

    public void setImgSearchUsername(String imgSearchUsername) {
        this.imgSearchUsername = imgSearchUsername;
        this.setFlag(FLAG_IMG_SEARCH_USERNAME, this.imgSearchUsername != null && !this.imgSearchUsername.trim().isEmpty());
    }

    public boolean hasStaticMapsProvider() {
        return this.isFlagSet(FLAG_STATIC_MAPS_PROVIDER);
    }
    
    public String getStaticMapsProvider() {
        return staticMapsProvider;
    }

    public void setStaticMapsProvider(String staticMapsProvider) {
        this.staticMapsProvider = staticMapsProvider;
        this.setFlag(FLAG_STATIC_MAPS_PROVIDER, this.staticMapsProvider != null && !this.staticMapsProvider.trim().isEmpty());
    }

    public int getCaptionLengthMax() {
        return captionLengthMax;
    }

    public void setCaptionLengthMax(int captionLengthMax) {
        this.captionLengthMax = captionLengthMax;
    }

    public int getMessageLengthMax() {
        return messageLengthMax;
    }

    public void setMessageLengthMax(int messageLengthMax) {
        this.messageLengthMax = messageLengthMax;
    }

    public int getWebfileDcId() {
        return webfileDcId;
    }

    public void setWebfileDcId(int webfileDcId) {
        this.webfileDcId = webfileDcId;
    }

    public boolean hasLangInformation() {
        return this.isFlagSet(FLAG_LANG);
    }
    
    public String getSuggestedLangCode() {
        return suggestedLangCode;
    }

    public void setSuggestedLangCode(String suggestedLangCode) {
        this.suggestedLangCode = suggestedLangCode;
        this.setFlag(FLAG_LANG, suggestedLangCode != null && !suggestedLangCode.trim().isEmpty());
    }

    public int getLangPackVersion() {
        return langPackVersion;
    }

    public void setLangPackVersion(int langPackVersion) {
        this.langPackVersion = langPackVersion;
        this.setFlag(FLAG_LANG, suggestedLangCode != null);
    }

    public int getBaseLangPackVersion() {
        return baseLangPackVersion;
    }

    public void setBaseLangPackVersion(int baseLangPackVersion) {
        this.baseLangPackVersion = baseLangPackVersion;
        this.setFlag(FLAG_LANG, suggestedLangCode != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.expires, stream);
        StreamingUtils.writeTLBool(this.testMode, stream);
        StreamingUtils.writeInt(this.thisDc, stream);
        StreamingUtils.writeTLVector(this.dcOptions, stream);
        StreamingUtils.writeTLString(this.dcTxtDomainName, stream);
        StreamingUtils.writeInt(this.chatSizeMax, stream);
        StreamingUtils.writeInt(this.megaGroupSizeMax, stream);
        StreamingUtils.writeInt(this.forwardedCountMax, stream);
        StreamingUtils.writeInt(this.onlineUpdatePeriodMs, stream);
        StreamingUtils.writeInt(this.offlineBlurTimeoutMs, stream);
        StreamingUtils.writeInt(this.offlineIdleTimeoutMs, stream);
        StreamingUtils.writeInt(this.onlineCloudTimeoutMs, stream);
        StreamingUtils.writeInt(this.notifyCloudDelayMs, stream);
        StreamingUtils.writeInt(this.notifyDefaultDelayMs, stream);
        StreamingUtils.writeInt(this.pushChatPeriodMs, stream);
        StreamingUtils.writeInt(this.pushChatLimit, stream);
        StreamingUtils.writeInt(this.savedGifsLimit, stream);
        StreamingUtils.writeInt(this.editTimeLimit, stream);
        StreamingUtils.writeInt(this.revokeTimeLimit, stream);
        StreamingUtils.writeInt(this.revokePmTimeLimit, stream);
        StreamingUtils.writeInt(this.ratingEDecay, stream);
        StreamingUtils.writeInt(this.stickersRecentLimit, stream);
        StreamingUtils.writeInt(this.stickersFavedLimit, stream);
        StreamingUtils.writeInt(this.channelsReadMediaPeriod, stream);
        if (this.hasTmpSessions()) {
            StreamingUtils.writeInt(this.tmpSessions, stream);
        }
        StreamingUtils.writeInt(this.pinnedDialogsCountMax, stream);
        StreamingUtils.writeInt(this.pinnedInfolderCountMax, stream);
        StreamingUtils.writeInt(this.callReceiveTimeoutMs, stream);
        StreamingUtils.writeInt(this.callRingTimeoutMs, stream);
        StreamingUtils.writeInt(this.callConnectTimeoutMs, stream);
        StreamingUtils.writeInt(this.callPacketTimeoutMs, stream);
        StreamingUtils.writeTLString(this.meUrlPrefix, stream);
        if (this.isAutoupdateUrlPrefix()) {
            StreamingUtils.writeTLString(this.autoupdateUrlPrefix, stream);
        }
        if (this.hasGifSearchUsername()) {
            StreamingUtils.writeTLString(this.gifSearchUsername, stream);
        }
        if (this.hasVenueSearchUsername()) {
            StreamingUtils.writeTLString(this.venueSearchUsername, stream);
        }
        if (this.hasImgSearchUsername()) {
            StreamingUtils.writeTLString(this.imgSearchUsername, stream);
        }
        if (this.hasStaticMapsProvider()) {
            StreamingUtils.writeTLString(this.staticMapsProvider, stream);
        }
        StreamingUtils.writeInt(this.captionLengthMax, stream);
        StreamingUtils.writeInt(this.messageLengthMax, stream);
        StreamingUtils.writeInt(this.webfileDcId, stream);
        if ((this.flags & FLAG_LANG) != 0) {
            StreamingUtils.writeTLString(this.suggestedLangCode, stream);
            StreamingUtils.writeInt(this.langPackVersion, stream);
            StreamingUtils.writeInt(this.baseLangPackVersion, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.expires = StreamingUtils.readInt(stream);
        this.testMode = StreamingUtils.readTLBool(stream);
        this.thisDc = StreamingUtils.readInt(stream);
        this.dcOptions = StreamingUtils.readTLVector(stream, context, TLDcOption.class);
        this.dcTxtDomainName = StreamingUtils.readTLString(stream);
        this.chatSizeMax = StreamingUtils.readInt(stream);
        this.megaGroupSizeMax = StreamingUtils.readInt(stream);
        this.forwardedCountMax = StreamingUtils.readInt(stream);
        this.onlineUpdatePeriodMs = StreamingUtils.readInt(stream);
        this.offlineBlurTimeoutMs = StreamingUtils.readInt(stream);
        this.offlineIdleTimeoutMs = StreamingUtils.readInt(stream);
        this.onlineCloudTimeoutMs = StreamingUtils.readInt(stream);
        this.notifyCloudDelayMs = StreamingUtils.readInt(stream);
        this.notifyDefaultDelayMs = StreamingUtils.readInt(stream);
        this.pushChatPeriodMs = StreamingUtils.readInt(stream);
        this.pushChatLimit = StreamingUtils.readInt(stream);
        this.savedGifsLimit = StreamingUtils.readInt(stream);
        this.editTimeLimit = StreamingUtils.readInt(stream);
        this.revokeTimeLimit = StreamingUtils.readInt(stream);
        this.revokePmTimeLimit = StreamingUtils.readInt(stream);
        this.ratingEDecay = StreamingUtils.readInt(stream);
        this.stickersRecentLimit = StreamingUtils.readInt(stream);
        this.stickersFavedLimit = StreamingUtils.readInt(stream);
        this.channelsReadMediaPeriod = StreamingUtils.readInt(stream);
        if (this.hasTmpSessions()) {
            this.tmpSessions = StreamingUtils.readInt(stream);
        }
        this.pinnedDialogsCountMax = StreamingUtils.readInt(stream);
        this.pinnedInfolderCountMax = StreamingUtils.readInt(stream);
        this.callReceiveTimeoutMs = StreamingUtils.readInt(stream);
        this.callRingTimeoutMs = StreamingUtils.readInt(stream);
        this.callConnectTimeoutMs = StreamingUtils.readInt(stream);
        this.callPacketTimeoutMs = StreamingUtils.readInt(stream);
        this.meUrlPrefix = StreamingUtils.readTLString(stream);
        if (this.isAutoupdateUrlPrefix()) {
            this.autoupdateUrlPrefix = StreamingUtils.readTLString(stream);
        }
        if (this.hasGifSearchUsername()) {
            this.gifSearchUsername = StreamingUtils.readTLString(stream);
        }
        if (this.hasVenueSearchUsername()) {
            this.venueSearchUsername = StreamingUtils.readTLString(stream);
        }
        if (this.hasImgSearchUsername()) {
            this.imgSearchUsername = StreamingUtils.readTLString(stream);
        }
        if (this.hasStaticMapsProvider()) {
            this.staticMapsProvider = StreamingUtils.readTLString(stream);
        }
        this.captionLengthMax = StreamingUtils.readInt(stream);
        this.messageLengthMax = StreamingUtils.readInt(stream);
        this.webfileDcId = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_LANG) != 0) {
            this.suggestedLangCode = StreamingUtils.readTLString(stream);
            this.langPackVersion = StreamingUtils.readInt(stream);
            this.baseLangPackVersion = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "config#330b4067";
    }

}