package org.javagram.api.channel.base.filters;

import org.javagram.api.message.base.TLMessageRange;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Channel messages filter
 *  * @since 18/SEP/2015
 */
public class TLChannelMessagesFilter extends TLAbsChannelMessagesFilter {
    public static final int CLASS_ID = 0xcd77d957;

    public static final int FLAG_IMPORTANT_ONLY          = 0x00000001; // 0
    public static final int FLAG_EXCLUDE_NEW_MESSAGES    = 0x00000002; // 1

    private TLVector<TLMessageRange> ranges;

    public TLChannelMessagesFilter() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLMessageRange> getRanges() {
        return ranges;
    }

    public void setRanges(TLVector<TLMessageRange> ranges) {
        this.ranges = ranges;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLVector(this.ranges, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.ranges = StreamingUtils.readTLVector(stream, context, TLMessageRange.class);
    }

    @Override
    public String toString() {
        return "channel.messages.filter.TLChannelMessagesFilter#cd77d957";
    }

}
