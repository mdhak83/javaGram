package org.telegram.api.messages.functions;

import org.telegram.api.chat.base.encrypted.TLAbsEncryptedChat;
import org.telegram.api.chat.base.input.encrypted.TLInputEncryptedChat;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Confirms creation of a secret chat
 * messages.acceptEncryption#3dbc0415 peer:InputEncryptedChat g_b:bytes key_fingerprint:long = EncryptedChat;
 */
public class TLRequestMessagesAcceptEncryption extends TLMethod<TLAbsEncryptedChat> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3dbc0415;

    /**
     * Secret chat ID
     */
    private TLInputEncryptedChat peer;

    /**
     * <code>B = g ^ b mod p</code>, see @see <a href="https://en.wikipedia.org/wiki/Diffie%E2%80%93Hellman_key_exchange">Wikipedia</a>
     */
    private TLBytes gB;

    /**
     * 64-bit fingerprint of the received key
     */
    private long keyFingerprint;

    public TLRequestMessagesAcceptEncryption() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLInputEncryptedChat getPeer() {
        return this.peer;
    }

    public void setPeer(TLInputEncryptedChat value) {
        this.peer = value;
    }

    public TLBytes getGB() {
        return this.gB;
    }

    public void setGB(TLBytes value) {
        this.gB = value;
    }

    public long getKeyFingerprint() {
        return this.keyFingerprint;
    }

    public void setKeyFingerprint(long value) {
        this.keyFingerprint = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLBytes(this.gB, stream);
        StreamingUtils.writeLong(this.keyFingerprint, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputEncryptedChat.class);
        this.gB = StreamingUtils.readTLBytes(stream, context);
        this.keyFingerprint = StreamingUtils.readLong(stream);
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
        return "messages.acceptEncryption#3dbc0415";
    }

}