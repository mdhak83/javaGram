package org.telegram.api;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** 
 * Represents a bot logged in using the @see <a href="https://core.telegram.org/widgets/login">Telegram login widget</a>
 * webAuthorization#cac943f2 hash:long bot_id:int domain:string browser:string platform:string date_created:int date_active:int ip:string region:string = WebAuthorization;
 */
public class TLWebAuthorization extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcac943f2;

    /**
     * Identifier
     */
    private long hash;
    
    /**
     * Bot ID
     */
    private int botId;
    
    /**
     * The domain name of the website on which the user has logged in.
     */
    private String domain;
    
    /**
     * Browser user-agent
     */
    private String browser;
    
    /**
     * Platform
     */
    private String platform;
    
    /**
     * When was the web session created
     */
    private int dateCreated;
    
    /**
     * When was the web session last active
     */
    private int dateActive;
    
    /**
     * IP address
     */
    private String ip;

    /**
     * Region, determined from IP address
     */
    private String region;

    public TLWebAuthorization() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.hash, stream);
        StreamingUtils.writeInt(this.botId, stream);
        StreamingUtils.writeTLString(this.domain, stream);
        StreamingUtils.writeTLString(this.browser, stream);
        StreamingUtils.writeTLString(this.platform, stream);
        StreamingUtils.writeInt(this.dateCreated, stream);
        StreamingUtils.writeInt(this.dateActive, stream);
        StreamingUtils.writeTLString(this.ip, stream);
        StreamingUtils.writeTLString(this.region, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readLong(stream);
        this.botId = StreamingUtils.readInt(stream);
        this.domain = StreamingUtils.readTLString(stream);
        this.browser = StreamingUtils.readTLString(stream);
        this.platform = StreamingUtils.readTLString(stream);
        this.dateCreated = StreamingUtils.readInt(stream);
        this.dateActive = StreamingUtils.readInt(stream);
        this.ip = StreamingUtils.readTLString(stream);
        this.region = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "webAuthorization#cac943f2";
    }

}