package org.javagram.api.update.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update channel new message
 */
public class TLUpdateChannelMessageViews extends TLAbsUpdate implements TLItfChannelUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x98a12b4b;

    private int channelId;
    private int id;
    private int views;

    public TLUpdateChannelMessageViews() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.channelId, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeInt(this.views, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channelId = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.views = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "update.TLUpdateChannelMessageViews#98a12b4b";
    }

}