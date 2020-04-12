package org.telegram.api.peer.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.client.handlers.AbstractUpdatesHandler;

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
        return this.channelId + "";
    }

}