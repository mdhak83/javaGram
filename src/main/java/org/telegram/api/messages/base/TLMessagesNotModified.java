package org.telegram.api.messages.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * No new messages matching the query were found
 * messages.messagesNotModified#74535f21 count:int = messages.Messages;
 */
public class TLMessagesNotModified extends TLAbsMessages {

    /**
     * 
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x74535f21;

    /**
     * Number of results found server-side by the given query
     */
    private int count;

    public TLMessagesNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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
        return "messages.messagesNotModified#74535f21";
    }

}