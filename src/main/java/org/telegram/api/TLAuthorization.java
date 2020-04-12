package org.telegram.api;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Logged-in session
 */
public class TLAuthorization extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xad01d61d;

    private static final int FLAG_CURRENT           = 0x00000001; // 0 : Whether this is the current session
    private static final int FLAG_OFFICIAL_APP      = 0x00000002; // 1 : Whether the session is from an official app
    private static final int FLAG_PASSWORD_PENDING  = 0x00000004; // 2 : Whether the session is still waiting for a 2FA password

    /**
     * Identifier
     */
    private long hash;
    
    /**
     * Device model
     */
    private String deviceModel;
    
    /**
     * Platform
     */
    private String platform;
    
    /**
     * System version
     */
    private String systemVersion;
    
    /**
     * @see <a href="https://core.telegram.org/api/obtaining_api_id">API ID</a>
     */
    private int apiId;
    
    /**
     * App name
     */
    private String appName;

    /**
     * App version
     */
    private String appVersion;

    /**
     * When was the session created
     */
    private int dateCreated;

    /**
     * When was the session last active
     */
    private int dateActive;
    
    /**
     * Last known IP
     */
    private String ip;
    
    /**
     * Country determined from IP
     */
    private String country;
    
    /**
     * Region determined from IP
     */
    private String region;

    public TLAuthorization() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isCurrent() {
        return this.isFlagSet(FLAG_CURRENT);
    }

    public boolean isOfficialApp() {
        return this.isFlagSet(FLAG_OFFICIAL_APP);
    }

    public boolean isPasswordPending() {
        return this.isFlagSet(FLAG_PASSWORD_PENDING);
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getDateActive() {
        return dateActive;
    }

    public void setDateActive(int dateActive) {
        this.dateActive = dateActive;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.hash, stream);
        StreamingUtils.writeTLString(this.deviceModel, stream);
        StreamingUtils.writeTLString(this.platform, stream);
        StreamingUtils.writeTLString(this.systemVersion, stream);
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.appName, stream);
        StreamingUtils.writeTLString(this.appVersion, stream);
        StreamingUtils.writeInt(this.dateCreated, stream);
        StreamingUtils.writeInt(this.dateActive, stream);
        StreamingUtils.writeTLString(this.ip, stream);
        StreamingUtils.writeTLString(this.country, stream);
        StreamingUtils.writeTLString(this.region, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.hash = StreamingUtils.readLong(stream);
        this.deviceModel = StreamingUtils.readTLString(stream);
        this.platform = StreamingUtils.readTLString(stream);
        this.systemVersion = StreamingUtils.readTLString(stream);
        this.apiId = StreamingUtils.readInt(stream);
        this.appName = StreamingUtils.readTLString(stream);
        this.appVersion = StreamingUtils.readTLString(stream);
        this.dateCreated = StreamingUtils.readInt(stream);
        this.dateActive = StreamingUtils.readInt(stream);
        this.ip = StreamingUtils.readTLString(stream);
        this.country = StreamingUtils.readTLString(stream);
        this.region = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "authorization#ad01d61d";
    }

}
