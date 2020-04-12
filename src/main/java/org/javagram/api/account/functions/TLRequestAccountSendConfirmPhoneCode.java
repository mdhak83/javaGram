package org.javagram.api.account.functions;

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
 * Send confirmation code to cancel account deletion, for more info @see <a href="https://core.telegram.org/api/account-deletion">click here »</a>
 * account.sendConfirmPhoneCode#1b3faa88 hash:string settings:CodeSettings = auth.SentCode;
 */
public class TLRequestAccountSendConfirmPhoneCode extends TLMethod<TLSentCode> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b3faa88;

    /**
     * The hash from the service notification, for more info @see <a href="https://core.telegram.org/api/account-deletion">click here »</a>
     */
    private String hash;
    
    /**
     * Phone code settings
     */
    private TLCodeSettings settings;

    public TLRequestAccountSendConfirmPhoneCode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public TLCodeSettings getSettings() {
        return settings;
    }

    public void setSettings(TLCodeSettings settings) {
        this.settings = settings;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.hash, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readTLString(stream);
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
            throw new IOException("Incorrect response type. Expected " + TLSentCode.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.sendConfirmPhoneCode#1b3faa88";
    }

}