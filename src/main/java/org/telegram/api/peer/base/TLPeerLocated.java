package org.telegram.api.peer.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * Peer geolocated nearby
 * peerLocated#ca461b5d peer:Peer expires:int distance:int = PeerLocated;
 */
public class TLPeerLocated extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xca461b5d;

    /**
     * Peer
     */
    private TLAbsPeer peer;
    
    /**
     * Validity period of current data
     */
    private int expires;

    /**
     * Distance from the peer in meters
     */
    private int distance;

    public TLPeerLocated() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.expires, stream);
        StreamingUtils.writeInt(this.distance, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.expires = StreamingUtils.readInt(stream);
        this.distance = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "peerLocated#ca461b5d";
    }

}