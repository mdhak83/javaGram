package org.javagram.api.channels.functions;

import org.javagram.api.messages.base.chats.TLMessagesChats;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.messages.base.chats.TLAbsMessagesChats;

/**
 * The type TL request channel get channels
 */
public class TLRequestChannelsGetChannels extends TLMethod<TLAbsMessagesChats> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa7f6bbb;

    private TLVector<TLAbsInputChannel> id;

    public TLRequestChannelsGetChannels() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsInputChannel> getId() {
        return id;
    }

    public void setId(TLVector<TLAbsInputChannel> id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLVector(stream, context, TLAbsInputChannel.class);
    }

    @Override
    public TLAbsMessagesChats deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessagesChats) {
            return (TLAbsMessagesChats) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.getChannels#a7f6bbb";
    }

}