package org.javagram.api.update.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.utils.StreamingUtils;

/**
 * updateChannel
 * A new channel is available
 * updateChannel#b6d45656 channel_id:int = Update;
 */
public class TLUpdateChannel extends TLAbsUpdate implements ITLUpdateChannel {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb6d45656;

    /**
     * Channel/supergroup ID
     */
    private int channelId;

    public TLUpdateChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateChannel#b6d45656";
    }

}