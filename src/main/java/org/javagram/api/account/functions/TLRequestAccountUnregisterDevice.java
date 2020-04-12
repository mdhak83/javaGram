package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLIntVector;

/**
 * Deletes a device by its token, stops sending PUSH-notifications to it.
 * account.unregisterDevice#3076c4bf token_type:int token:string other_uids:Vector&lt;int&gt; = Bool;
 */
public class TLRequestAccountUnregisterDevice extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3076c4bf;
    
    /**
     * Device token type.
     * <strong>Possible values:</strong>
     * <ul><li><code>1</code> - APNS (device token for apple push)</li>
     * <li><code>2</code> - FCM (firebase token for google firebase)</li>
     * <li><code>3</code> - MPNS (channel URI for microsoft push)</li>
     * <li><code>4</code> - Simple push (endpoint for firefox's simple push API)</li>
     * <li><code>5</code> - Ubuntu phone (token for ubuntu push)</li>
     * <li><code>6</code> - Blackberry (token for blackberry push)</li>
     * <li><code>7</code> - Unused</li>
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
     * List of user identifiers of other users currently using the client
     */
    private TLIntVector otherUids;
    
    public TLRequestAccountUnregisterDevice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public TLIntVector getOtherUids() {
        return otherUids;
    }

    public void setOtherUids(TLIntVector otherUids) {
        this.otherUids = otherUids;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.tokenType, stream);
        StreamingUtils.writeTLString(this.token, stream);
        StreamingUtils.writeTLVector(this.otherUids, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.tokenType = StreamingUtils.readInt(stream);
        this.token = StreamingUtils.readTLString(stream);
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
        return "account.unregisterDevice#3076c4bf";
    }

}