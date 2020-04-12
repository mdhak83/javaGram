package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.TLAbsMessages;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.input.TLAbsInputMessage;
import org.telegram.api._primitives.TLVector;

/**
 * Returns the list of messages by their IDs.
 * messages.getMessages#63c66506 id:Vector&lt;InputMessage&gt; = messages.Messages;
 */
public class TLRequestMessagesGetMessages extends TLMethod<TLAbsMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x63c66506;

    /**
     * Message ID list
     */
    private TLVector<TLAbsInputMessage> id;

    public TLRequestMessagesGetMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsInputMessage> getId() {
        return this.id;
    }

    public void setId(TLVector<TLAbsInputMessage> value) {
        this.id = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLVector(stream, context, TLAbsInputMessage.class);
    }

    @Override
    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessages) {
            return (TLAbsMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAbsMessages, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getMessages#63c66506";
    }

}