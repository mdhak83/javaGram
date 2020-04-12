package org.javagram.api.auth.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.auth.base.logintoken.TLAbsAuthLoginToken;
import org.javagram.api._primitives.TLBytes;

/**
 * Login using a redirected login token, generated in case of DC mismatch during @see <a href="https://core.telegram.org/api/qr-login">QR code login</a>.
 * For more info, see @see <a href="https://core.telegram.org/api/qr-login">login via QR code</a>.
 * auth.importLoginToken#95ac5ce4 token:bytes = auth.LoginToken;
 */
public class TLRequestAuthImportLoginToken extends TLMethod<TLAbsAuthLoginToken> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x95ac5ce4;

    /**
     * Login token
     */
    private TLBytes token;

    public TLRequestAuthImportLoginToken() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getToken() {
        return token;
    }

    public void setToken(TLBytes token) {
        this.token = token;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(this.token, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.token = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public TLAbsAuthLoginToken deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsAuthLoginToken) {
            return (TLAbsAuthLoginToken) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "auth.importLoginToken#95ac5ce4";
    }

}