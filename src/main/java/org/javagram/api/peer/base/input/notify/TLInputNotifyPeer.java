package org.javagram.api.peer.base.input.notify;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input notify peer.
 */
public class TLInputNotifyPeer extends TLAbsInputNotifyPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb8bc5b0c;

    private TLAbsInputPeer peer;

    public TLInputNotifyPeer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param value the value
     */
    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
    }

    @Override
    public String toString() {
        return "inputNotifyPeer#b8bc5b0c";
    }

}