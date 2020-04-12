package org.telegram.api.update.base;

import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * New incoming scheduled message
 * updateNewScheduledMessage#39a51dfb message:Message = Update;
 */
public class TLUpdateNewScheduledMessage extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x39a51dfb;

    /**
     * Message
     */
    private TLAbsMessage message;

    public TLUpdateNewScheduledMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessage getMessage() {
        return this.message;
    }

    public void setMessage(TLAbsMessage value) {
        this.message = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.message = StreamingUtils.readTLObject(stream, context, TLAbsMessage.class);
    }

    @Override
    public String toString() {
        return "updateNewScheduledMessage#39a51dfb";
    }
    
}