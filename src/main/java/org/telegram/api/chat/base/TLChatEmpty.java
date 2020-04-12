package org.telegram.api.chat.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * chatEmpty
 * Empty constructor, group doesn't exist
 * chatEmpty#9ba2d800 id:int = Chat;
 */
public class TLChatEmpty extends TLAbsChat {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9ba2d800;

    public TLChatEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public boolean isChannel() {
        return false;
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
        return "chatEmpty#9ba2d800";
    }

    @Override
    public String toLog() {
        return ("ChatEmpty (id=" + String.format("%08x", this.id) + ")");
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {
    }
    
}