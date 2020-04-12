package org.javagram.api.messages.functions;

import org.javagram.api.messages.base.chats.TLMessagesChats;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns chat basic info on their IDs.
 * messages.getChats#3c6aa187 id:Vector&lt;int&gt; = messages.Chats;
 */
public class TLRequestMessagesGetChats extends TLMethod<TLMessagesChats> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3c6aa187;

    /**
     * List of chat IDs
     */
    private TLIntVector id;

    public TLRequestMessagesGetChats() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLIntVector getId() {
        return id;
    }

    public void setId(TLIntVector id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public TLMessagesChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesChats) {
            return (TLMessagesChats) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getChats#3c6aa187";
    }

}