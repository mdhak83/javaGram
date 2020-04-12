package org.telegram.api.message.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Empty constructor, non-existent message.
 * messageEmpty#83e5de54 id:int = Message;
 */
public class TLMessageEmpty extends TLAbsMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x83e5de54;
    
    /**
     * Message identifier
     */
    private int id;

    public TLMessageEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getChatId() {
        return 0;
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
        return "messageEmpty#83e5de54";
    }

}