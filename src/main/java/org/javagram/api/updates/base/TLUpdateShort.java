package org.javagram.api.updates.base;

import org.javagram.api.update.base.TLAbsUpdate;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update short.
 */
public class TLUpdateShort extends TLAbsUpdates {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x78d4dec1;

    /**
     * Update
     */
    private TLAbsUpdate update;
    
    /**
     * Date of event
     */
    private int date;

    public TLUpdateShort() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdate getUpdate() {
        return this.update;
    }

    public void setUpdate(TLAbsUpdate update) {
        this.update = update;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.update, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.update = StreamingUtils.readTLObject(stream, context, TLAbsUpdate.class);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateShort#78d4dec1";
    }

}