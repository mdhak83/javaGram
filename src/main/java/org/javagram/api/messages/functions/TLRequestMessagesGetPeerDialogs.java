package org.javagram.api.messages.functions;

import org.javagram.api.messages.base.TLMessagesPeerDialogs;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.dialog.base.input.TLAbsInputDialogPeer;

/**
 * Get dialog info of specified peers
 * messages.getPeerDialogs#e470bcfd peers:Vector&lt;InputDialogPeer&gt; = messages.PeerDialogs;
 */
public class TLRequestMessagesGetPeerDialogs extends TLMethod<TLMessagesPeerDialogs> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe470bcfd;

    /**
     * Peers
     */
    private TLVector<TLAbsInputDialogPeer> peers;

    public TLRequestMessagesGetPeerDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsInputDialogPeer> getPeers() {
        return peers;
    }

    public void setPeers(TLVector<TLAbsInputDialogPeer> peers) {
        this.peers = peers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.peers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peers = StreamingUtils.readTLVector(stream, context, TLAbsInputDialogPeer.class);
    }

    @Override
    public TLMessagesPeerDialogs deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesPeerDialogs) {
            return (TLMessagesPeerDialogs) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesPeerDialogs.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getPeerDialogs#e470bcfd";
    }

}