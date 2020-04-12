package org.javagram.api.update.base;

import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update read messages outbox.
 */
public class TLUpdateReadMessagesOutbox extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2f2f21bf;

    private int pts;
    private int maxId;
    private int ptsCount;
    private TLAbsPeer peer;

    public TLUpdateReadMessagesOutbox() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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
     * Gets max id.
     *
     * @return the max id
     */
    public int getMaxId() {
        return this.maxId;
    }

    /**
     * Sets max id.
     *
     * @param maxId the max id
     */
    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    /**
     * Gets pts count.
     *
     * @return the pts count
     */
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

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param peer the peer
     */
    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.maxId = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateReadMessageOutbox#2f2f21bf";
    }

}