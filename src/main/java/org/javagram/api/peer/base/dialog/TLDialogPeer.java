package org.javagram.api.peer.base.dialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;

/**
 * Peer, or all peers in a folder
 * dialogPeer#e56dbf05 peer:Peer = DialogPeer;
 */
public class TLDialogPeer extends TLAbsDialogPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe56dbf05;

    /**
     * Peer
     */
    protected TLAbsPeer peer;

    public TLDialogPeer() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
    }

    @Override
    public String toString() {
        return "dialogPeer#e56dbf05";
    }
    
}