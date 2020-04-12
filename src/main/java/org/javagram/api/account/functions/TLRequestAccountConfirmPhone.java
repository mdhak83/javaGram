package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Confirm a phone number to cancel account deletion, for more info @see <a href="https://core.telegram.org/api/account-deletion">click here »</a>
 * account.confirmPhone#5f2178c3 phone_code_hash:string phone_code:string = Bool;
 */
public class TLRequestAccountConfirmPhone extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5f2178c3;

    /**
     * Phone code hash, for more info @see <a href="https://core.telegram.org/api/account-deletion">click here »</a>
     */
    private String phoneCodeHash;
    
    /**
     * SMS code, for more info @see <a href="https://core.telegram.org/api/account-deletion">click here »</a>
     */
    private String phoneCode;

    public TLRequestAccountConfirmPhone() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhoneCodeHash() {
        return phoneCodeHash;
    }

    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.phoneCodeHash, stream);
        StreamingUtils.writeTLString(this.phoneCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneCodeHash = StreamingUtils.readTLString(stream);
        this.phoneCode = StreamingUtils.readTLString(stream);
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
        return "account.confirmPhone#5f2178c3";
    }

}