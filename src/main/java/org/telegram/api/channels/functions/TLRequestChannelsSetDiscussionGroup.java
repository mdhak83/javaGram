package org.telegram.api.channels.functions;

import org.telegram.api.messages.base.TLMessagesChatFull;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api._primitives.TLBool;

/**
 * Associate a group to a channel as @see <a href="https://telegram.org/blog/privacy-discussions-web-bots">discussion group</a> for that channel
 * channels.setDiscussionGroup#40582bb2 broadcast:InputChannel group:InputChannel = Bool;
 */
public class TLRequestChannelsSetDiscussionGroup extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x40582bb2;
    
    /**
     * Channel
     */
    private TLAbsInputChannel broadcast;
    
    /**
     * Discussion group to associate to the channel
     */
    private TLAbsInputChannel group;

    public TLRequestChannelsSetDiscussionGroup() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputChannel getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(TLAbsInputChannel broadcast) {
        this.broadcast = broadcast;
    }

    public TLAbsInputChannel getGroup() {
        return group;
    }

    public void setGroup(TLAbsInputChannel group) {
        this.group = group;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.broadcast, stream);
        StreamingUtils.writeTLObject(this.group, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.broadcast = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.group = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChatFull.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.setDiscussionGroup#40582bb2";
    }

}