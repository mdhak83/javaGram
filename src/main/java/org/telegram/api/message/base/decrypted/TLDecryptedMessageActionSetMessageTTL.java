package org.telegram.api.message.base.decrypted;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action set message tTL.
 */
public class TLDecryptedMessageActionSetMessageTTL extends TLDecryptedMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa1733aec;

    private int ttlSeconds;

    public TLDecryptedMessageActionSetMessageTTL() {
    }

    /**
     * Instantiates a new TL decrypted message action set message tTL.
     *
     * @param _ttlSeconds the _ ttl seconds
     */
    public TLDecryptedMessageActionSetMessageTTL(int _ttlSeconds) {
        this.ttlSeconds = _ttlSeconds;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets ttl seconds.
     *
     * @return the ttl seconds
     */
    public int getTtlSeconds() {
        return this.ttlSeconds;
    }

    /**
     * Sets ttl seconds.
     *
     * @param ttlSeconds the ttl seconds
     */
    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.ttlSeconds, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.ttlSeconds = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionSetMessageTTL#a1733aec";
    }

}