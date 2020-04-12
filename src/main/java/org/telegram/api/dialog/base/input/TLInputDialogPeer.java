package org.telegram.api.dialog.base.input;

import org.telegram.api.message.base.input.TLAbsInputMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * Peer, or all peers in a certain folder
 * inputDialogPeer#fcaafeb7 peer:InputPeer = InputDialogPeer;
 */
public class TLInputDialogPeer extends TLAbsInputMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfcaafeb7;
    
    /**
     * Peer
     */
    private TLAbsInputPeer peer;

    public TLInputDialogPeer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
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
        return "inputDialogPeer#fcaafeb7";
    }

}