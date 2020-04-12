package org.telegram.api.channels.functions;

import org.telegram.api.messages.base.TLMessagesChatFull;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.messages.base.chats.TLAbsMessagesChats;

/**
 * Get a list of @see <a href="https://core.telegram.org/api/channel">channels/supergroups</a> we left
 * channels.getLeftChannels#8341ecc0 offset:int = messages.Chats;
 */
public class TLRequestChannelsGetLeftChannels extends TLMethod<TLAbsMessagesChats> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8341ecc0;

    /**
     * Offset for @see <a href="https://core.telegram.org/api/offsets">pagination</a>
     */
    private int offset;

    public TLRequestChannelsGetLeftChannels() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.offset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offset = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsMessagesChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesChats) {
            return (TLAbsMessagesChats) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChatFull.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.getLeftChannels#8341ecc0";
    }

}