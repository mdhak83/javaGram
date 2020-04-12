package org.javagram.api.auth.functions;

import org.javagram.api.auth.base.sentcode.TLSentCode;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.TLCodeSettings;

/**
 * Send the verification code for login
 * auth.sendCode#a677244f phone_number:string api_id:int api_hash:string settings:CodeSettings = auth.SentCode;
 */
public class TLRequestAuthSendCode extends TLMethod<TLSentCode> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa677244f;

    /**
     * Phone number in international format
     */
    private String phoneNumber;
    
    /**
     * Application identifier (see @see <a href ="https://core.telegram.org/myapp">App configuration</a>)
     */
    private int apiId;
    
    /**
     * Application secret hash (see @see <a href ="https://core.telegram.org/myapp">App configuration</a>)
     */
    private String apiHash;
    
    /**
     * Settings for the code type to send
     */
    private TLCodeSettings settings;

    public TLRequestAuthSendCode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    public int getApiId() {
        return this.apiId;
    }

    public void setApiId(int value) {
        this.apiId = value;
    }

    public String getApiHash() {
        return this.apiHash;
    }

    public void setApiHash(String value) {
        this.apiHash = value;
    }

    public TLCodeSettings getSettings() {
        return settings;
    }

    public void setSettings(TLCodeSettings settings) {
        this.settings = settings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.apiHash, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneNumber = StreamingUtils.readTLString(stream);
        this.apiId = StreamingUtils.readInt(stream);
        this.apiHash = StreamingUtils.readTLString(stream);
        this.settings = StreamingUtils.readTLObject(stream, context, TLCodeSettings.class);
    }

    @Override
    public TLSentCode deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLSentCode) {
            return (TLSentCode) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLSentCode, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "auth.sendCode#a677244f";
    }

}