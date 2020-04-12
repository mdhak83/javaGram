package org.telegram.api.channels.functions;

import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Toggle supergroup slow mode: if enabled, users will only be able to send one message every <code>seconds</code> seconds
 * channels.toggleSlowMode#edd49ef0 channel:InputChannel seconds:int = Updates;
 */
public class TLRequestChannelsToggleSlowMode extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedd49ef0;

    /**
     * The @see <a href="https://core.telegram.org/api/channel">supergroup</a>
     */
    private TLAbsInputChannel channel;

    /**
     * Users will only be able to send one message every <code>seconds</code> seconds, <code>0</code> to disable the limitation
     */
    private int seconds;

    public TLRequestChannelsToggleSlowMode() {
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

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeInt(this.seconds, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context,TLAbsInputChannel.class);
        this.seconds = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.toggleSlowMode#edd49ef0";
    }

}