package org.telegram.api.channels.functions;

import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api.messages.base.TLAbsMessages;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.input.TLAbsInputMessage;
import org.telegram.api._primitives.TLVector;

/**
 * Get @see <a href="https://core.telegram.org/api/channel">channel/supergroup</a> messages
 * channels.getMessages#ad8c9a23 channel:InputChannel id:Vector&lt;InputMessage&gt; = messages.Messages;
 */
public class TLRequestChannelsGetMessages extends TLMethod<TLAbsMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xad8c9a23;

    /**
     * Channel/supergroup
     */
    private TLAbsInputChannel channel;
    
    /**
     * IDs of messages to get
     */
    private TLVector<TLAbsInputMessage> id;

    public TLRequestChannelsGetMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLVector<TLAbsInputMessage> getId() {
        return id;
    }

    public void setId(TLVector<TLAbsInputMessage> id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.id = StreamingUtils.readTLVector(stream, context, TLAbsInputMessage.class);
    }

    @Override
    public TLAbsMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsMessages) {
            return (TLAbsMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsMessages.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.getMessages#ad8c9a23";
    }

}