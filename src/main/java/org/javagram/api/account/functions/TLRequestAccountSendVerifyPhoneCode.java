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
 * Send the verification phone code for telegram @see <a href="https://core.telegram.org/passport">passport</a>.
 * account.sendVerifyPhoneCode#a5a356f9 phone_number:string settings:CodeSettings = auth.SentCode;
 */
public class TLRequestAccountSendVerifyPhoneCode extends TLMethod<TLSentCode> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa5a356f9;

    /**
     * The phone number to verify
     */
    private String phoneNumber;
    
    /**
     * Phone code settings
     */
    private TLCodeSettings settings;

    public TLRequestAccountSendVerifyPhoneCode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneNumber = StreamingUtils.readTLString(stream);
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
        return "account.sendVerifyPhoneCode#a5a356f9";
    }

}