package org.telegram.api.message.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Message to which the specified message replies to
 * inputMessageReplyTo#bad88395 id:int = InputMessage;
 */
public class TLInputMessageReplyTo extends TLAbsInputMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbad88395;

    /**
     * ID of the message that replies to the message we need
     */
    private int id;

    public TLInputMessageReplyTo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputMessageReplyTo#bad88395";
    }

}