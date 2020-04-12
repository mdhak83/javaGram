package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.messages.base.TLAffectedHistory;
import org.telegram.api.peer.base.input.TLAbsInputPeer;

/**
 * Mark mentions as read
 * messages.readMentions#f0189d3 peer:InputPeer = messages.AffectedHistory;
 */
public class TLRequestMessagesReadMentions extends TLMethod<TLAffectedHistory> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf0189d3;

    /**
     * Dialog
     */
    private TLAbsInputPeer peer;
    
    public TLRequestMessagesReadMentions() {
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
    public TLAffectedHistory deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAffectedHistory) {
            return (TLAffectedHistory) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.messages.TLAbsMessages, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.readMentions#f0189d3";
    }

}