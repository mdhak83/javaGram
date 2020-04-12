package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.secure.base.TLSecureValueHash;
import org.javagram.api.secure.base.credentials.TLSecureCredentialsEncrypted;
import org.javagram.api._primitives.TLVector;

/**
 * Sends a Telegram Passport authorization form, effectively sharing data with the service
 * account.acceptAuthorization#e7027c94 bot_id:int scope:string public_key:string value_hashes:Vector&lt;SecureValueHash&gt; credentials:SecureCredentialsEncrypted = Bool;
 */
public class TLRequestAccountAcceptAuthorization extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe7027c94;

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
    
    /**
     * Types of values sent and their hashes
     */
    private TLVector<TLSecureValueHash> valueHashes;
    
    /**
     * Encrypted values
     */
    private TLSecureCredentialsEncrypted credentials;
    
    public TLRequestAccountAcceptAuthorization() {
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

    public TLVector<TLSecureValueHash> getValueHashes() {
        return valueHashes;
    }

    public void setValueHashes(TLVector<TLSecureValueHash> valueHashes) {
        this.valueHashes = valueHashes;
    }

    public TLSecureCredentialsEncrypted getCredentials() {
        return credentials;
    }

    public void setCredentials(TLSecureCredentialsEncrypted credentials) {
        this.credentials = credentials;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.botId, stream);
        StreamingUtils.writeTLString(this.scope, stream);
        StreamingUtils.writeTLString(this.publicKey, stream);
        StreamingUtils.writeTLVector(this.valueHashes, stream);
        StreamingUtils.writeTLObject(this.credentials, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.botId = StreamingUtils.readInt(stream);
        this.scope = StreamingUtils.readTLString(stream);
        this.publicKey = StreamingUtils.readTLString(stream);
        this.valueHashes = StreamingUtils.readTLVector(stream, context, TLSecureValueHash.class);
        this.credentials = StreamingUtils.readTLObject(stream, context, TLSecureCredentialsEncrypted.class);
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
        return "account.acceptAuthorization#e7027c94";
    }

}