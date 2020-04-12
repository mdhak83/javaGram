package org.telegram.api.messages.functions;

import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.peer.base.TLPeerSettings;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get peer settings
 * messages.getPeerSettings#3672e09c peer:InputPeer = PeerSettings;
 */
public class TLRequestMessagesGetPeerSettings extends TLMethod<TLPeerSettings> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3672e09c;

    /**
     * The peer
     */
    private TLAbsInputUser peer;

    public TLRequestMessagesGetPeerSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputUser peer) {
        this.peer = peer;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
    }

    @Override
    public TLPeerSettings deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLPeerSettings) {
            return (TLPeerSettings) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLPeerSettings.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getPeerSettings#3672e09c";
    }

}