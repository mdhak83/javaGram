package org.javagram.api.update.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.api._primitives.TLVector;

/**
 * Some scheduled messages were deleted
 * updateDeleteScheduledMessages#90866cee peer:Peer messages:Vector&lt;int&gt; = Update;
 */
public class TLUpdateDeleteScheduledMessage extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x90866cee;

    /**
     * Peer
     */
    private TLAbsPeer peer;
    
    /**
     * Deleted scheduled messages
     */
    private TLVector<Integer> messages;

    public TLUpdateDeleteScheduledMessage() {
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
    
    public TLVector<Integer> getMessages() {
        return this.messages;
    }

    public void setMessages(TLVector<Integer> messages) {
        this.messages = messages;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.messages, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.messages = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "updateDeleteScheduledMessages#90866cee";
    }

}