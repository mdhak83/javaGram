package org.telegram.api.update.base;

import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.message.base.TLMessage;
import org.telegram.api.message.base.TLMessageService;
import org.telegram.api.peer.base.TLAbsPeer;
import org.telegram.api.peer.base.TLPeerChannel;
import org.telegram.api.peer.base.TLPeerChat;

/**
 * The type TL update channel new message
 */
public class TLUpdateChannelNewMessage extends TLAbsUpdate implements TLItfChannelUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x62ba04d9;

    private TLAbsMessage message;
    private int pts;
    private int ptsCount;

    public TLUpdateChannelNewMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsMessage getMessage() {
        return message;
    }

    public void setMessage(TLAbsMessage message) {
        this.message = message;
    }

    @Override
    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

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
        return "update.TLUpdateChannelNewMessage#62ba04d9";
    }
    
}