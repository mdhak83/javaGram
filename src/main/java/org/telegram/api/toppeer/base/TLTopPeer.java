package org.telegram.api.toppeer.base;

import org.telegram.api.peer.base.TLAbsPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Top peer
 * topPeer#edcdc05b peer:Peer rating:double = TopPeer;
 */
public class TLTopPeer extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedcdc05b;

    /**
     * Peer
     */
    private TLAbsPeer peer;
    
    /**
     * Rating as computer in @see <a href="https://core.telegram.org/api/top-rating">top peer rating</a>
     */
    private double rating;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeDouble(this.rating, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.rating = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "topPeer#edcdc05b";
    }

}