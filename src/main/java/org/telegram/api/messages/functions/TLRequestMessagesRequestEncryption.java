package org.telegram.api.messages.functions;

import org.telegram.api.chat.base.encrypted.TLAbsEncryptedChat;
import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Sends a request to start a secret chat to the user.
 * messages.requestEncryption#f64daf43 user_id:InputUser random_id:int g_a:bytes = EncryptedChat;
 */
public class TLRequestMessagesRequestEncryption extends TLMethod<TLAbsEncryptedChat> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf64daf43;

    /**
     * User ID
     */
    private TLAbsInputUser userId;

    /**
     * Unique client request ID required to prevent resending. This also doubles as the chat ID.
     */
    private int randomId;

    /**
     * <code>A = g ^ a mod p</code>, see @see <a href="https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange">Wikipedia</a>
     */
    private TLBytes gA;

    public TLRequestMessagesRequestEncryption() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getUserId() {
        return this.userId;
    }

    public void setUserId(TLAbsInputUser value) {
        this.userId = value;
    }

    public int getRandomId() {
        return this.randomId;
    }

    public void setRandomId(int value) {
        this.randomId = value;
    }

    public TLBytes getGA() {
        return this.gA;
    }

    public void setGA(TLBytes value) {
        this.gA = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.randomId, stream);
        StreamingUtils.writeTLBytes(this.gA, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.randomId = StreamingUtils.readInt(stream);
        this.gA = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public TLAbsEncryptedChat deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsEncryptedChat) {
            return (TLAbsEncryptedChat) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.encrypted.chat.TLAbsEncryptedChat, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.requestEncryption#f64daf43";
    }

}