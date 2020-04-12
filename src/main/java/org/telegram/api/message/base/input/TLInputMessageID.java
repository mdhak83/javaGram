package org.telegram.api.message.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Message by ID
 * inputMessageID#a676a322 id:int = InputMessage;
 */
public class TLInputMessageID extends TLAbsInputMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa676a322;

    /**
     * Message ID
     */
    private int id;

    public TLInputMessageID() {
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
        return "inputMessageID#a676a322";
    }

}