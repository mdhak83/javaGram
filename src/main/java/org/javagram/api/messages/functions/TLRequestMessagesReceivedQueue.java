package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLMethod;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Confirms receipt of messages in a secret chat by client, cancels push notifications.
 * messages.receivedQueue#55a5bb66 max_qts:int = Vector&lt;long&gt;;
 */
public class TLRequestMessagesReceivedQueue extends TLMethod<TLLongVector> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x55a5bb66;

    /**
     * Maximum qts value available at the client
     */
    private int maxQts;

    public TLRequestMessagesReceivedQueue() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getMaxQts() {
        return this.maxQts;
    }

    public void setMaxQts(int value) {
        this.maxQts = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.maxQts, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.maxQts = StreamingUtils.readInt(stream);
    }

    @Override
    public TLLongVector deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.receivedQueue#55a5bb66";
    }

}