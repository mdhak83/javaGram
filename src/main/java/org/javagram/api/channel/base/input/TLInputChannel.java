package org.javagram.api.channel.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents a channel
 * inputChannel#afeb712e channel_id:int access_hash:long = InputChannel;
 */
public class TLInputChannel extends TLAbsInputChannel {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xafeb712e;

    /**
     * Channel ID
     */
    private int channelId;
    
    /**
     * Access hash taken from the @see <a href="https://core.telegram.org/constructor/channel">channel</a> constructor
     */
    private long accessHash;

    public TLInputChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "inputChannel#afeb712e";
    }

    @Override
    public String toLog() {
        return "InputChannel(id=" + String.format("%08x", this.channelId) + ";accessHash=" + String.format("%016x", this.accessHash) + ")";
    }
    
}