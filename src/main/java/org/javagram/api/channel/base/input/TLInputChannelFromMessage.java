package org.javagram.api.channel.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.input.TLAbsInputPeer;

/**
 * Defines a @see <a href="https://core.telegram.org/api/min">min</a> channel that was seen in a certain message of a certain chat.
 * inputChannelFromMessage#2a286531 peer:InputPeer msg_id:int channel_id:int = InputChannel;
 */
public class TLInputChannelFromMessage extends TLAbsInputChannel {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2a286531;

    /**
     * The chat where the channel was seen
     */
    private TLAbsInputPeer peer; 
    
    /**
     * The message ID in the chat where the channel was seen
     */
    private int msgId;

    /**
     * The channel ID
     */
    private int channelId;
    
    public TLInputChannelFromMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.msgId, stream);
        StreamingUtils.writeInt(this.channelId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.msgId = StreamingUtils.readInt(stream);
        this.channelId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputChannelFromMessage#2a286531";
    }

    @Override
    public String toLog() {
        return "InputChannel(id=" + String.format("%08x", this.channelId) + ";msgId=" + String.format("%08x", this.msgId) + ";peer=" + this.peer.toLog() + ")";
    }
    
}