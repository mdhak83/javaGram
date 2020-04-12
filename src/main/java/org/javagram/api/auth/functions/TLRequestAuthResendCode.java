package org.javagram.api.auth.functions;

import org.javagram.api.auth.base.sentcode.TLSentCode;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Resend the login code via another medium, the phone code type is determined by the return value of the previous auth.sendCode/auth.resendCode: see @see <a href="https://core.telegram.org/api/auth">login</a> for more info.
 * auth.resendCode#3ef1a9bf phone_number:string phone_code_hash:string = auth.SentCode;
 */
public class TLRequestAuthResendCode extends TLMethod<TLSentCode> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3ef1a9bf;

    /**
     * The phone number
     */
    private String phoneNumber;
    
    /**
     * The phone code hash obtained from @see <a href="https://core.telegram.org/method/auth.sendCode">auth.sendCode</a>
     */
    private String phoneCodeHash;

    public TLRequestAuthResendCode() {
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

    public String getPhoneCodeHash() {
        return phoneCodeHash;
    }

    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        StreamingUtils.writeTLString(this.phoneCodeHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneNumber = StreamingUtils.readTLString(stream);
        this.phoneCodeHash = StreamingUtils.readTLString(stream);
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
        return "auth.resendCode#3ef1a9bf";
    }
    
}