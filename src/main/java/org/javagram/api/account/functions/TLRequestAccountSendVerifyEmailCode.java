package org.javagram.api.account.functions;

import org.javagram.api.auth.base.sentcode.TLSentCode;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.account.base.TLAccountSentEmailCode;

/**
 * Send the verification email code for telegram @see <a href="https://core.telegram.org/passport">passport</a>.
 * account.sendVerifyEmailCode#7011509f email:string = account.SentEmailCode;
 */
public class TLRequestAccountSendVerifyEmailCode extends TLMethod<TLAccountSentEmailCode> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7011509f;

    /**
     * The email where to send the code
     */
    private String email;
    
    public TLRequestAccountSendVerifyEmailCode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.email, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.email = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAccountSentEmailCode deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountSentEmailCode) {
            return (TLAccountSentEmailCode) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLSentCode.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.sendVerifyEmailCode#7011509f";
    }

}