package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Verify an email to use as @see <a href="https://core.telegram.org/api/srp">2FA recovery method</a>.
 * account.confirmPasswordEmail#8fdf1920 code:string = Bool;
 */
public class TLRequestAccountConfirmPasswordEmail extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8fdf1920;

    /**
     * The phone code that was received after @see <a href="https://core.telegram.org/api/srp#email-verification">setting a recovery email</a>
     */
    private String code;

    public TLRequestAccountConfirmPasswordEmail() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.code, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.code = StreamingUtils.readTLString(stream);
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
        return "account.confirmPasswordEmail#8fdf1920";
    }

}