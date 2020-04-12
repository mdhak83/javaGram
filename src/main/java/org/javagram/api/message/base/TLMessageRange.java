package org.javagram.api.message.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicates a range of chat messages
 * messageRange#ae30253 min_id:int max_id:int = MessageRange;
 */
public class TLMessageRange extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae30253;

    /**
     * Start of range (message ID)
     */
    private int minId;
    
    /**
     * End of range (message ID)
     */
    private int maxId;

    public TLMessageRange() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.minId, stream);
        StreamingUtils.writeInt(this.maxId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.minId = StreamingUtils.readInt(stream);
        this.maxId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageRange#ae30253";
    }

}