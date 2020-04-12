package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.account.base.TLAccountAuthorizationForm;

/**
 * Returns a Telegram Passport authorization form for sharing data with a service
 * account.getAuthorizationForm#b86ba8e1 bot_id:int scope:string public_key:string = account.AuthorizationForm;
 */
public class TLRequestAccountGetAuthorizationForm extends TLMethod<TLAccountAuthorizationForm> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb86ba8e1;

    /**
     * User identifier of the service's bot
     */
    private int botId;
    
    /**
     * Telegram Passport element types requested by the service
     */
    private String scope;
    
    /**
     * Service's public key
     */
    private String publicKey;
    
    public TLRequestAccountGetAuthorizationForm() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.botId, stream);
        StreamingUtils.writeTLString(this.scope, stream);
        StreamingUtils.writeTLString(this.publicKey, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.botId = StreamingUtils.readInt(stream);
        this.scope = StreamingUtils.readTLString(stream);
        this.publicKey = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAccountAuthorizationForm deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountAuthorizationForm) {
            return (TLAccountAuthorizationForm) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getAuthorizationForm#b86ba8e1";
    }

}