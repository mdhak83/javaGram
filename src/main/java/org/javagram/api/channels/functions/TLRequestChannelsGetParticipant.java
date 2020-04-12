package org.javagram.api.channels.functions;

import org.javagram.api.channels.base.TLChannelsChannelParticipant;
import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.user.base.input.TLAbsInputUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel get participant
 */
public class TLRequestChannelsGetParticipant extends TLMethod<TLChannelsChannelParticipant> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x546dd7a6;

    private TLAbsInputChannel channel;
    private TLAbsInputUser userId;

    public TLRequestChannelsGetParticipant() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLChannelsChannelParticipant deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLChannelsChannelParticipant) {
            return (TLChannelsChannelParticipant) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLChannelsChannelParticipant.class.getName() +", got: " + res.getClass().getName());
        }
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context,TLAbsInputChannel.class);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public String toString() {
        return "functions.channels.TLRequestChannelsGetParticipant#546dd7a6";
    }

}