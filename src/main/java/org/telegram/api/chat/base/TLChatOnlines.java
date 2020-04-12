package org.telegram.api.chat.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * Number of online users in a chat
 * chatOnlines#f041e250 onlines:int = ChatOnlines;
 */
public class TLChatOnlines extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf041e250;

    /**
     * Number of online users
     */
    private int onlines;

    public TLChatOnlines() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getOnlines() {
        return onlines;
    }

    public void setOnlines(int onlines) {
        this.onlines = onlines;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.onlines, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.onlines = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "chatOnlines#f041e250";
    }

}