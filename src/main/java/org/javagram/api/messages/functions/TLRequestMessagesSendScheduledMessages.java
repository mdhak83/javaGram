package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.api._primitives.TLIntVector;

/**
 * Send scheduled messages right away
 * messages.sendScheduledMessages#bd38850a peer:InputPeer id:Vector&lt;int&gt; = Updates;
 */
public class TLRequestMessagesSendScheduledMessages extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbd38850a;

    /**
     * Peer
     */
    private TLAbsInputPeer peer;
    
    /**
     * Scheduled message IDs
     */
    private TLIntVector id;

    public TLRequestMessagesSendScheduledMessages() {
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

    public TLIntVector getId() {
        return id;
    }

    public void setId(TLIntVector id) {
        this.id = id;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.id = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAbsMessages, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.sendScheduledMessages#bd38850a";
    }

}