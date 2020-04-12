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
import org.javagram.api._primitives.TLIntVector;

/**
 * Generate a login token, for @see <a href="https://core.telegram.org/api/qr-login">login via QR code</a>.
 * The generated login token should be encoded using base64url, then shown as a <code>tg://login?token=base64encodedtoken</code> URL in the QR code.
 * For more info, see @see <a href="https://core.telegram.org/api/qr-login">login via QR code</a>.
 * auth.exportLoginToken#b1b41517 api_id:int api_hash:string except_ids:Vector&lt;int&gt; = auth.LoginToken;
 */
public class TLRequestAuthExportLoginToken extends TLMethod<TLAbsAuthLoginToken> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb1b41517;

    /**
     * Application identifier (see. @see <a href="https://core.telegram.org/myapp">App configuration</a>)
     */
    private int apiId;
    
    /**
     * Application identifier hash (see. @see <a href="https://core.telegram.org/myapp">App configuration</a>)
     */
    private String apiHash;
    
    /**
     * List of already logged-in user IDs, to prevent logging in twice with the same user
     */
    private TLIntVector exceptIds;

    public TLRequestAuthExportLoginToken() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getApiHash() {
        return apiHash;
    }

    public void setApiHash(String apiHash) {
        this.apiHash = apiHash;
    }

    public TLIntVector getExceptIds() {
        return exceptIds;
    }

    public void setExceptIds(TLIntVector exceptIds) {
        this.exceptIds = exceptIds;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.apiHash, stream);
        StreamingUtils.writeTLVector(this.exceptIds, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.apiId = StreamingUtils.readInt(stream);
        this.apiHash = StreamingUtils.readTLString(stream);
        this.exceptIds = StreamingUtils.readTLIntVector(stream, context);
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
        return "auth.exportLoginToken#b1b41517";
    }

}