package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.input.reportreason.TLAbsReportReason;
import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api._primitives.TLIntVector;

/**
 * Report a message in a chat for violation of telegram's Terms of Service
 * messages.report#bd82b658 peer:InputPeer id:Vector&lt;int&gt; reason:ReportReason = Bool;
 */
public class TLRequestMessagesReport extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbd82b658;

    /**
     * Peer
     */
    private TLAbsInputPeer peer;
    
    /**
     * IDs of messages to report
     */
    private TLIntVector id;
    
    /**
     * Why are these messages being reported
     */
    private TLAbsReportReason reason;

    public TLRequestMessagesReport() {
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

    public TLAbsReportReason getReason() {
        return reason;
    }

    public void setReason(TLAbsReportReason reason) {
        this.reason = reason;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLVector(this.id, stream);
        StreamingUtils.writeTLObject(this.reason, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.id = StreamingUtils.readTLIntVector(stream, context);
        this.reason = StreamingUtils.readTLObject(stream, context, TLAbsReportReason.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.report#bd82b658";
    }

}