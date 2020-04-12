package org.javagram.api.channels.functions;

import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.messages.base.TLAffectedHistory;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;

/**
 * Delete the history of a @see <a href="https://core.telegram.org/api/channel">supergroup</a>
 * channels.deleteHistory#af369d42 channel:InputChannel max_id:int = Bool;
 */
public class TLRequestChannelsDeleteHistory extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaf369d42;

    /**
     * @see <a href="https://core.telegram.org/api/channel">supergroup</a> whose history must be deleted
     */
    private TLAbsInputChannel channel;
    
    /**
     * ID of message <strong>up to which</strong> the history must be deleted
     */
    private int maxId;

    public TLRequestChannelsDeleteHistory() {
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

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeInt(this.maxId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.maxId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAffectedHistory.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.deleteHistory#af369d42";
    }

}