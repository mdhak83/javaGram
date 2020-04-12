package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLIntVector;

/**
 * account.registerDevice
 * Register device to receive @see <a href="https://core.telegram.org/api/push-updates">PUSH notifications</a>
 * account.registerDevice#68976c6f flags:# no_muted:flags.0?true token_type:int token:string app_sandbox:Bool secret:bytes other_uids:Vector&lt;int&gt; = Bool;
 */
public class TLRequestAccountRegisterDevice extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x68976c6f;

    private static final int FLAG_NO_MUTED  = 0x00000001; // 0 : Avoid receiving (silent and invisible background) notifications. Useful to save battery.

    /**
     * Device token type.
     * <strong>Possible values:</strong>
     * <ul><li><code>1</code> - APNS (device token for apple push)</li>
     * <li><code>2</code> - FCM (firebase token for google firebase)</li>
     * <li><code>3</code> - MPNS (channel URI for microsoft push)</li>
     * <li><code>4</code> - Simple push (endpoint for firefox's simple push API)</li>
     * <li><code>5</code> - Ubuntu phone (token for ubuntu push)</li>
     * <li><code>6</code> - Blackberry (token for blackberry push)</li>
     * <li><code>7</code> - MTProto separate session</li>
     * <li><code>8</code> - WNS (windows push)</li>
     * <li><code>9</code> - APNS VoIP (token for apple push VoIP)</li>
     * <li><code>10</code> - Web push (web push, see below)</li>
     * <li><code>11</code> - MPNS VoIP (token for microsoft push VoIP)</li>
     * <li><code>12</code> - Tizen (token for tizen push)</li>
     * <li>For <code>10</code> web push, the token must be a JSON-encoded object containing the keys described in PUSH updates</li></ul>
     */
    private int tokenType;
    
    /**
     * Device token
     */
    private String token;
    
    /**
     * If (@see <a href="https://core.telegram.org/constructor/boolTrue">boolTrue</a>) is transmitted, a sandbox-certificate will be used during transmission.
     */
    private boolean appSandbox;
    
    /**
     * For FCM and APNS VoIP, optional encryption key used to encrypt push notifications
     */
    private TLBytes secret;
    
    /**
     * List of user identifiers of other users currently using the client
     */
    private TLIntVector otherUids;
    
    public TLRequestAccountRegisterDevice() {
        super();
    }

    public TLRequestAccountRegisterDevice(int tokenType, String token, boolean appSandbox) {
        super();
        this.tokenType = tokenType;
        this.token = token;
        this.appSandbox = appSandbox;
        this.secret = new TLBytes(new byte[] {});
        this.otherUids = new TLIntVector();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isNoMuted() {
        return this.isFlagSet(FLAG_NO_MUTED);
    }
    
    public void setNoMuted(boolean value) {
        this.setFlag(FLAG_NO_MUTED, value);
    }

    public int getTokenType() {
        return tokenType;
    }

    public void setTokenType(int tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAppSandbox() {
        return appSandbox;
    }

    public void setAppSandbox(boolean appSandbox) {
        this.appSandbox = appSandbox;
    }

    public TLBytes getSecret() {
        return secret;
    }

    public void setSecret(TLBytes secret) {
        this.secret = secret;
    }

    public TLIntVector getOtherUids() {
        return otherUids;
    }

    public void setOtherUids(TLIntVector otherUids) {
        this.otherUids = otherUids;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.tokenType, stream);
        StreamingUtils.writeTLString(this.token, stream);
        StreamingUtils.writeTLBool(this.appSandbox, stream);
        StreamingUtils.writeTLBytes(this.secret, stream);
        StreamingUtils.writeTLVector(this.otherUids, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.tokenType = StreamingUtils.readInt(stream);
        this.token = StreamingUtils.readTLString(stream);
        this.appSandbox = StreamingUtils.readTLBool(stream);
        this.secret = StreamingUtils.readTLBytes(stream, context);
        this.otherUids = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.registerDevice#68976c6f";
    }

}