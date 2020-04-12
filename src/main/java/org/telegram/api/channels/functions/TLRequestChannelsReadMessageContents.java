package org.telegram.api.channels.functions;

import org.telegram.api.messages.base.chats.TLMessagesChats;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLIntVector;

/**
 * Mark @see <a href="https://core.telegram.org/api/channel">channel/supergroup</a> message contents as read
 * channels.readMessageContents#eab5dc38 channel:InputChannel id:Vector&lt;int&gt; = Bool;
 */
public class TLRequestChannelsReadMessageContents extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeab5dc38;

    /**
     * @see <a href="https://core.telegram.org/api/channel">Channel/supergroup</a>
     */
    private TLAbsInputChannel channel;
    
    /**
     * IDs of messages whose contents should be marked as read
     */
    private TLIntVector id;
    
    public TLRequestChannelsReadMessageContents() {
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

    public TLIntVector getId() {
        return id;
    }

    public void setId(TLIntVector id) {
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
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.readMessageContents#eab5dc38";
    }

}