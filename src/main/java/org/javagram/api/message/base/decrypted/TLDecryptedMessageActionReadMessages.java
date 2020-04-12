package org.javagram.api.message.base.decrypted;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL decrypted message action read messages.
 */
public class TLDecryptedMessageActionReadMessages extends TLDecryptedMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc4f40be;

    private TLLongVector randomIds = new TLLongVector();

    public TLDecryptedMessageActionReadMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets random ids.
     *
     * @return the random ids
     */
    public TLLongVector getRandomIds() {
        return this.randomIds;
    }

    /**
     * Sets random ids.
     *
     * @param randomIds the random ids
     */
    public void setRandomIds(TLLongVector randomIds) {
        this.randomIds = randomIds;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.randomIds, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.randomIds = StreamingUtils.readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "decryptedMessageActionSetMessageTTL#c4f40be";
    }

}