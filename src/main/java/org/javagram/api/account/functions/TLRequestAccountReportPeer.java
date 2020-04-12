package org.javagram.api.account.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.input.reportreason.TLAbsReportReason;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Report a peer for violation of telegram's Terms of Service
 * account.reportPeer#ae189d5f peer:InputPeer reason:ReportReason = Bool;
 */
public class TLRequestAccountReportPeer extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae189d5f;

    /**
     * The peer to report
     */
    private TLAbsInputPeer peer;
    
    /**
     * The reason why this peer is being reported
     */
    private TLAbsReportReason reason;

    public TLRequestAccountReportPeer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsReportReason getReason() {
        return reason;
    }

    public void setReason(TLAbsReportReason reason) {
        this.reason = reason;
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
        StreamingUtils.writeTLObject(this.reason, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
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
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.reportPeer#ae189d5f";
    }

}