package org.javagram.api.channels.functions;

import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.messages.base.TLMessagesChatFull;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel get messages
 */
public class TLRequestChannelsGetFullChannel extends TLMethod<TLMessagesChatFull> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8736a09;

    private TLAbsInputChannel channel;

    public TLRequestChannelsGetFullChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLMessagesChatFull deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesChatFull) {
            return (TLMessagesChatFull) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChatFull.class.getName() +", got: " + res.getClass().getName());
        }
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
    }

    @Override
    public String toString() {
        return "functions.channels.TLRequestChannelsGetFullChannel#8736a09";
    }

}