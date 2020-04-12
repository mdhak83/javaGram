package org.javagram.api.auth.base.logintoken;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;

/**
 * auth.loginToken
 * Login token (for @see <a href="https://core.telegram.org/api/qr-login">QR code login</a>)
 * auth.loginToken#629f1980 expires:int token:bytes = auth.LoginToken;
 */
public class TLAuthLoginToken extends TLAbsAuthLoginToken {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x629f1980;

    /**
     * Expiry date of QR code
     */
    private int expires;
    
    /**
     * Token to render in QR code
     */
    private TLBytes token;

    public TLAuthLoginToken() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public TLBytes getToken() {
        return token;
    }

    public void setToken(TLBytes token) {
        this.token = token;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.expires, stream);
        StreamingUtils.writeTLBytes(this.token, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.expires = StreamingUtils.readInt(stream);
        this.token = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "auth.loginToken#629f1980";
    }

}