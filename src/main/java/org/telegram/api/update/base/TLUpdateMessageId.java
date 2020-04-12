package org.telegram.api.update.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.client.handlers.AbstractUpdatesHandler;

/**
 * The type TL update message iD.
 */
public class TLUpdateMessageId extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4e90bfd6;

    private int id;
    private long randomId;

    public TLUpdateMessageId() {
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

    public long getRandomId() {
        return this.randomId;
    }

    public void setRandomId(long randomId) {
        this.randomId = randomId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeLong(this.randomId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readInt(stream);
        this.randomId = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "updateMessageID#4e90bfd6";
    }

    public String toLog() {
        return "ID: " + this.id + " - RandomID : " + this.randomId;
    }

}