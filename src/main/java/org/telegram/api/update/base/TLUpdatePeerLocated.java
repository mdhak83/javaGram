package org.telegram.api.update.base;

import org.telegram.api.peer.base.TLPeerLocated;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLVector;

/**
 * List of peers near you was updated
 * updatePeerLocated#b4afcfb0 peers:Vector&lt;PeerLocated&gt; = Update;
 */
public class TLUpdatePeerLocated extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb4afcfb0;
    
    /**
     * Geolocated peer list update
     */
    private TLVector<TLPeerLocated> peers;
    
    public TLUpdatePeerLocated() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public TLVector<TLPeerLocated> getPeers() {
        return peers;
    }

    public void setPeers(TLVector<TLPeerLocated> peers) {
        this.peers = peers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.peers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peers = StreamingUtils.readTLVector(stream, context, TLPeerLocated.class);
    }

    @Override
    public String toString() {
        return "updatePeerLocated#b4afcfb0";
    }

}