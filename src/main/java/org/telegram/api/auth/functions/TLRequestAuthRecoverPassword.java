package org.telegram.api.auth.functions;

import org.telegram.api.auth.base.authorization.TLAbsAuthAuthorization;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Reset the @see <a href="https://core.telegram.org/api/srp">2FA password</a> using the recovery code sent using @see <a href="https://core.telegram.org/method/auth.requestPasswordRecovery">auth.requestPasswordRecovery</a>.
 * auth.recoverPassword#4ea56e92 code:string = auth.Authorization;
 */
public class TLRequestAuthRecoverPassword extends TLMethod<TLAbsAuthAuthorization> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4ea56e92;

    /**
     * Code received via email
     */
    private String code;

    public TLRequestAuthRecoverPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public TLAbsAuthAuthorization deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsAuthAuthorization) {
            return (TLAbsAuthAuthorization) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.auth.TLAuthorization, got: " + res.getClass().getCanonicalName());
        }
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
    public String toString() {
        return "auth.recoveryPassword#4ea56e92";
    }

}