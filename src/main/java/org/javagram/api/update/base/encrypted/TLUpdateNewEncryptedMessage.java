package org.javagram.api.update.base.encrypted;

import org.javagram.api.message.base.encrypted.TLAbsEncryptedMessage;
import org.javagram.api.update.base.TLAbsUpdate;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update new encrypted message.
 */
public class TLUpdateNewEncryptedMessage extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x12bcbd9a;

    private int qts;
    private TLAbsEncryptedMessage message;

    public TLUpdateNewEncryptedMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets qts.
     *
     * @return the qts
     */
    public int getQts() {
        return this.qts;
    }

    /**
     * Sets qts.
     *
     * @param qts the qts
     */
    public void setQts(int qts) {
        this.qts = qts;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public TLAbsEncryptedMessage getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(TLAbsEncryptedMessage message) {
        this.message = message;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.message, stream);
        StreamingUtils.writeInt(this.qts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.message = StreamingUtils.readTLObject(stream, context, TLAbsEncryptedMessage.class);
        this.qts = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateNewEncryptedMessage#12bcbd9a";
    }

}