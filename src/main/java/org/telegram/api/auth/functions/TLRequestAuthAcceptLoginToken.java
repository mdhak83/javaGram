package org.telegram.api.auth.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.TLAuthorization;
import org.telegram.api._primitives.TLBytes;

/**
 * Accept QR code login token, logging in the app that generated it.
 * Returns info about the new session.
 * For more info, see @see <a href="https://core.telegram.org/api/qr-login">login via QR code</a>.
 * auth.acceptLoginToken#e894ad4d token:bytes = Authorization;
 */
public class TLRequestAuthAcceptLoginToken extends TLMethod<TLAuthorization> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe894ad4d;

    /**
     * Login token embedded in QR code, for more info, see @see <a href="https://core.telegram.org/api/qr-login">login via QR code</a>.
     */
    private TLBytes token;

    public TLRequestAuthAcceptLoginToken() {
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
    public TLAuthorization deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAuthorization) {
            return (TLAuthorization) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "auth.acceptLoginToken#e894ad4d";
    }

}