package org.javagram.api.peer.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Defines a channel for further interaction.
 * inputPeerChannel#20adaef8 channel_id:int access_hash:long = InputPeer;
 */
public class TLInputPeerChannel extends TLAbsInputPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x20adaef8;

    /**
     * Channel identifier
     */
    private int channelId;
    
    /**
     * <strong>access_hash</strong> value from the @sse <a href="https://core.telegram.org/constructor/channel">channel</a> constructor
     */
    private long accessHash;

    public TLInputPeerChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getId() {
        return this.channelId;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public long getAccessHash() {
        return this.accessHash;
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
        return "inputPeerChannel#20adaef8";
    }

}