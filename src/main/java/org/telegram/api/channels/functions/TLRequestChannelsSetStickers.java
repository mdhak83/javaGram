package org.telegram.api.channels.functions;

import org.telegram.api.messages.base.chats.TLMessagesChats;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.channel.base.input.TLAbsInputChannel;
import org.telegram.api.sticker.base.input.set.TLAbsInputStickerSet;
import org.telegram.api._primitives.TLBool;

/**
 * Associate a stickerset to the supergroup
 * channels.setStickers#ea8ca4f9 channel:InputChannel stickerset:InputStickerSet = Bool;
 */
public class TLRequestChannelsSetStickers extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xea8ca4f9;

    /**
     * Supergroup
     */
    private TLAbsInputChannel channel;
    
    /**
     * The stickerset to associate
     */
    private TLAbsInputStickerSet stickerset;

    public TLRequestChannelsSetStickers() {
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

    public TLAbsInputStickerSet getStickerset() {
        return stickerset;
    }

    public void setStickerset(TLAbsInputStickerSet stickerset) {
        this.stickerset = stickerset;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.stickerset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context, TLAbsInputChannel.class);
        this.stickerset = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChats.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.setStickers#ea8ca4f9";
    }

}