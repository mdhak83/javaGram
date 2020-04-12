package org.javagram.api.auth.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.auth.base.authorization.TLAbsAuthAuthorization;
import org.javagram.api.password.base.input.checkpassword.TLAbsInputCheckPasswordSRP;

/**
 * Try logging to an account protected by a @see <a href="https://core.telegram.org/api/srp">2FA password</a>.
 * auth.checkPassword#d18b4d16 password:InputCheckPasswordSRP = auth.Authorization;
 */
public class TLRequestAuthCheckPassword extends TLMethod<TLAbsAuthAuthorization> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd18b4d16;

    /**
     * The account's password (see @see <a href="https://core.telegram.org/api/srp">SRP</a>)
     */
    private TLAbsInputCheckPasswordSRP password;

    public TLRequestAuthCheckPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputCheckPasswordSRP getPassword() {
        return password;
    }

    public void setPassword(TLAbsInputCheckPasswordSRP password) {
        this.password = password;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.password, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.password = StreamingUtils.readTLObject(stream, context, TLAbsInputCheckPasswordSRP.class);
    }

    @Override
    public TLAbsAuthAuthorization deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsAuthAuthorization) { 
            return (TLAbsAuthAuthorization) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.auth.TLAuthorization, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "auth.checkPassword#d18b4d16";
    }

}