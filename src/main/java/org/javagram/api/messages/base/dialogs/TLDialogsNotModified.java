package org.javagram.api.messages.base.dialogs;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Dialogs haven't changed
 * messages.dialogsNotModified#f0e3e596 count:int = messages.Dialogs;
 */
public class TLDialogsNotModified extends TLAbsDialogs {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf0e3e596;

    private int count;

    public TLDialogsNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int value) {
        this.count = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.count, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.count = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messages.dialogsNotModified#f0e3e596";
    }
    
}