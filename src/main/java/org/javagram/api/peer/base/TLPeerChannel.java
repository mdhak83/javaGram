package org.javagram.api.peer.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Channel/supergroup
 * peerChannel#bddde532 channel_id:int = Peer;
 */
public class TLPeerChannel extends TLAbsPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbddde532;
    
    /**
     * Channel ID
     */
    private int channelId;

    public TLPeerChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getId() {
        return channelId;
    }

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
        return "peerChannel#bddde532";
    }

    @Override
    public String toLog() {
        return "PeerChannel#" + String.format("%08x", this.channelId);
    }

}