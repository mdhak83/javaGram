package org.telegram.api.update.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update new message.
 */
public class TLUpdateChannelPinnedMessage extends TLAbsUpdate implements TLItfChannelUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x98592475;

    private int channelId;
    private int id;

    public TLUpdateChannelPinnedMessage() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeInt(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateChannelPinnedMessage#98592475";
    }

}