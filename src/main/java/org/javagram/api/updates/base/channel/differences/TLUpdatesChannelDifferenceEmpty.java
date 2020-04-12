package org.javagram.api.updates.base.channel.differences;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * updates.channelDifferenceEmpty
 * There are no new updates
 * updates.channelDifferenceEmpty#3e11affb flags:# final:flags.0?true pts:int timeout:flags.1?int = updates.ChannelDifference;
 */
public class TLUpdatesChannelDifferenceEmpty extends TLAbsUpdatesChannelDifference {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3e11affb;
    
    /**
     * The latest @see <a href="https://core.telegram.org/api/updates">PTS</a>
     */
    private int pts;

    public TLUpdatesChannelDifferenceEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.pts, stream);
        if (this.hasTimeout()) {
            StreamingUtils.writeInt(this.timeout, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        if (this.hasTimeout()) {
            this.timeout = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "updates.channelDifferenceEmpty#3e11affb";
    }

}