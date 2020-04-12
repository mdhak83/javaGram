package org.javagram.api.messages.functions;

import org.javagram.api.chat.base.input.encrypted.TLInputEncryptedChat;
import org.javagram.api.messages.base.sentencrypted.TLAbsSentEncryptedMessage;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Sends a text message to a secret chat.
 * messages.sendEncrypted#a9776773 peer:InputEncryptedChat random_id:long data:bytes = messages.SentEncryptedMessage;
 */
public class TLRequestMessagesSendEncrypted extends TLMethod<TLAbsSentEncryptedMessage> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa9776773;

    /**
     * Secret chat ID
     */
    private TLInputEncryptedChat peer;

    /**
     * Unique client message ID, necessary to avoid message resending
     */
    private long randomId;

    /**
     * TL-serialization of @see <a href="https://core.telegram.org/type/DecryptedMessage">DecryptedMessage</a> type, encrypted with a key that was created during chat initialization
     */
    private TLBytes data;

    public TLRequestMessagesSendEncrypted() {
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

    public long getRandomId() {
        return this.randomId;
    }

    public void setRandomId(long value) {
        this.randomId = value;
    }

    public TLBytes getData() {
        return this.data;
    }

    public void setData(TLBytes value) {
        this.data = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeTLBytes(this.data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputEncryptedChat.class);
        this.randomId = StreamingUtils.readLong(stream);
        this.data = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public TLAbsSentEncryptedMessage deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsSentEncryptedMessage) {
            return (TLAbsSentEncryptedMessage) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.messages.sentencrypted.TLAbsSentEncryptedMessage, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.sendEncrypted#a9776773";
    }

}