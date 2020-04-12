package org.telegram.api.account.functions;

import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Change the phone number of the current account
 * account.changePhone#70c32edb phone_number:string phone_code_hash:string phone_code:string = User;
 */
public class TLRequestAccountChangePhone extends TLMethod<TLAbsUser> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x70c32edb;

    /**
     * New phone number
     */
    private String phoneNumber;

    /**
     * Phone code hash received when calling @see <a href="https://core.telegram.org/method/account.sendChangePhoneCode">account.sendChangePhoneCode</a>
     */
    private String phoneCodeHash;

    /**
     * Phone code received when calling @see <a href="https://core.telegram.org/method/account.sendChangePhoneCode">account.sendChangePhoneCode</a>
     */
    private String phoneCode;

    public TLRequestAccountChangePhone() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCode() {
        return this.phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneCodeHash() {
        return this.phoneCodeHash;
    }

    public void setPhoneCodeHash(String phoneCodeHash) {
        this.phoneCodeHash = phoneCodeHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.phoneNumber, stream);
        StreamingUtils.writeTLString(this.phoneCodeHash, stream);
        StreamingUtils.writeTLString(this.phoneCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.phoneNumber = StreamingUtils.readTLString(stream);
        this.phoneCodeHash = StreamingUtils.readTLString(stream);
        this.phoneCode = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsUser deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUser) {
            return (TLAbsUser) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAbsUser, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.sendChangePhone#70c32edb";
    }

}