package org.telegram.api.auth.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Binds a temporary authorization key <code>temp_auth_key_id</code> to the permanent authorization key <code>perm_auth_key_id</code>.
 * Each permanent key may only be bound to one temporary key at a time, binding a new temporary key overwrites the previous one.
 * For more information, see @see <a href="https://core.telegram.org/api/pfs">Perfect Forward Secrecy</a>.
 * auth.bindTempAuthKey#cdd42a05 perm_auth_key_id:long nonce:long expires_at:int encrypted_message:bytes = Bool;
 */
public class TLRequestAuthBindTempAuthKey extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcdd42a05;

    /**
     * Permanent auth_key_id to bind to
     */
    private long permAuthKeyId;

    /**
     * Random long from @see <a href="https://core.telegram.org/method/auth.bindTempAuthKey#binding-message-contents">Binding message contents</a>
     */
    private long nonce;

    /**
     * Unix timestamp to invalidate temporary key, see @see <a href="https://core.telegram.org/method/auth.bindTempAuthKey#binding-message-contents">Binding message contents</a>
     */
    private int expiresAt;

    /**
     * See @see <a href="https://core.telegram.org/method/auth.bindTempAuthKey#generating-encrypted-message">Generating encrypted_message</a>
     */
    private TLBytes encryptedMessage;

    public TLRequestAuthBindTempAuthKey() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.permAuthKeyId, stream);
        StreamingUtils.writeLong(this.nonce, stream);
        StreamingUtils.writeInt(this.expiresAt, stream);
        StreamingUtils.writeTLBytes(this.encryptedMessage, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.permAuthKeyId = StreamingUtils.readLong(stream);
        this.nonce = StreamingUtils.readLong(stream);
        this.expiresAt = StreamingUtils.readInt(stream);
        this.encryptedMessage = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "auth.bindTempAuthKey#cdd42a05";
    }

}