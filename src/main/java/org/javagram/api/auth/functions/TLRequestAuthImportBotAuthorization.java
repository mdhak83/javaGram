package org.javagram.api.auth.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.auth.base.authorization.TLAbsAuthAuthorization;

/**
 * Login as a bot
 * auth.importBotAuthorization#67a3ff2c flags:int api_id:int api_hash:string bot_auth_token:string = auth.Authorization;
 */
public class TLRequestAuthImportBotAuthorization extends TLMethod<TLAbsAuthAuthorization> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x67a3ff2c;

    /**
     * Application identifier (see. @see <a href="https://core.telegram.org/myapp">App configuration</a>)
     */
    private int apiId;

    /**
     * Application identifier hash (see. @see <a href="https://core.telegram.org/myapp">App configuration</a>)
     */
    private String apiHash;

    /**
     * Bot token (see @see <a href="https://core.telegram.org/bots">bots</a>)
     */
    private String botAuthToken;

    public TLRequestAuthImportBotAuthorization() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getApiId() {
        return this.apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getApiHash() {
        return this.apiHash;
    }

    public void setApiHash(String apiHash) {
        this.apiHash = apiHash;
    }

    public String getBotAuthToken() {
        return this.botAuthToken;
    }

    public void setBotAuthToken(String botAuthToken) {
        this.botAuthToken = botAuthToken;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.apiId, stream);
        StreamingUtils.writeTLString(this.apiHash, stream);
        StreamingUtils.writeTLString(this.botAuthToken, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.apiId = StreamingUtils.readInt(stream);
        this.apiHash = StreamingUtils.readTLString(stream);
        this.botAuthToken = StreamingUtils.readTLString(stream);
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
        return "auth.importBotAuthorization#67a3ff2c";
    }

}