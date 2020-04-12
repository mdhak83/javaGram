package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.messages.base.TLAffectedMessages;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Marks message history as read.
 * messages.readHistory#e306d3a peer:InputPeer max_id:int = messages.AffectedMessages;
 */
public class TLRequestMessagesReadHistory extends TLMethod<TLAffectedMessages> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe306d3a;

    /**
     * Target user or group
     */
    private TLAbsInputPeer peer;

    /**
     * If a positive value is passed, only messages with identifiers less or equal than the given one will be read
     */
    private int maxId;

    public TLRequestMessagesReadHistory() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
    }

    public int getMaxId() {
        return this.maxId;
    }

    public void setMaxId(int value) {
        this.maxId = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.maxId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAffectedMessages deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAffectedMessages) {
            return (TLAffectedMessages) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAffectedMessages.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.readHistory#e306d3a";
    }

}