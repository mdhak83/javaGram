package org.javagram.api.messages.functions;

import org.javagram.api.TLReceivedNotifyMessage;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Confirms receipt of messages by a client, cancels PUSH-notification sending.
 * messages.receivedMessages#5a954c0 max_id:int = Vector&lt;ReceivedNotifyMessage&gt;;
 */
public class TLRequestMessagesReceivedMessages extends TLMethod<TLVector<TLReceivedNotifyMessage>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5a954c0;

    /**
     * Maximum message ID available in a client.
     */
    private int maxId;

    public TLRequestMessagesReceivedMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getMaxId() {
        return this.maxId;
    }

    public void setMaxId(int value) {
        this.maxId = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.maxId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.maxId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLVector<TLReceivedNotifyMessage> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLReceivedNotifyMessage.class);
    }

    @Override
    public String toString() {
        return "messages.receivedMessages#5a954c0";
    }

}