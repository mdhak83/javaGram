package org.javagram.api.update.base;

import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.message.base.TLMessage;
import org.javagram.api.message.base.TLMessageService;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.api.peer.base.TLPeerChannel;
import org.javagram.api.peer.base.TLPeerChat;

/**
 * The type TL update new message.
 */
public class TLUpdateEditChannelMessage extends TLAbsUpdate implements ITLUpdateChannel {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b3f4df7;

    private int pts;
    private int ptsCount;
    private TLAbsMessage message;

    public TLUpdateEditChannelMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public TLAbsMessage getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param value the value
     */
    public void setMessage(TLAbsMessage value) {
        this.message = value;
    }

    /**
     * Gets pts.
     *
     * @return the pts
     */
    public int getPts() {
        return this.pts;
    }

    /**
     * Sets pts.
     *
     * @param pts the pts
     */
    public void setPts(int pts) {
        this.pts = pts;
    }

    /**
     * Gets pts count.
     *
     * @return the pts count
     */
    @Override
    public int getPtsCount() {
        return this.ptsCount;
    }

    /**
     * Sets pts count.
     *
     * @param ptsCount the pts count
     */
    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    @Override
    public int getChannelId() {
        int ret = 0;
        TLAbsPeer peer = null;
        if (this.message instanceof TLMessage) {
            peer = ((TLMessage) this.message).getToId();
        } else if (this.message instanceof TLMessageService) {
            peer = ((TLMessageService) this.message).getToId();
        }
        if (peer != null) {
            if (peer instanceof TLPeerChannel) {
                ret = ((TLPeerChannel) peer).getChannelId();
            } else if (peer instanceof TLPeerChat) {
                ret = ((TLPeerChat) peer).getChatId();
            }
        }
        return ret;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.message, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.message = StreamingUtils.readTLObject(stream, context, TLAbsMessage.class);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateEditChannelMessage#1b3f4df7";
    }

}