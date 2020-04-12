package org.telegram.api.channels.functions;

import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api.message.base.TLExportedMessageLink;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get link and embed info of a message in a @see <a href="https://core.telegram.org/api/channel">supergroup/channel</a>
 * channels.exportMessageLink#ceb77163 channel:InputChannel id:int grouped:Bool = ExportedMessageLink;
 */
public class TLRequestChannelsExportMessageLink extends TLMethod<TLExportedMessageLink> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xceb77163;

    /**
     * Channel
     */
    private TLAbsInputChannel channel;

    /**
     * Message ID
     */
    private int id;

    /**
     * Whether to include other grouped media (for albums)
     */
    private boolean grouped;

    public TLRequestChannelsExportMessageLink() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGrouped() {
        return grouped;
    }

    public void setGrouped(boolean grouped) {
        this.grouped = grouped;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeTLBool(this.grouped, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.id = StreamingUtils.readInt(stream);
        this.grouped = StreamingUtils.readTLBool(stream);
    }

    @Override
    public TLExportedMessageLink deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLExportedMessageLink) {
            return (TLExportedMessageLink) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLExportedMessageLink.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.exportMessageLink#ceb77163";
    }

}