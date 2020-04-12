package org.telegram.api.phone.functions;

import org.telegram.api.phone.base.call.input.TLInputPhoneCall;
import org.telegram.api.phone.base.TLPhoneCallProtocol;
import org.telegram.api.phone.base.TLPhonePhoneCall;
import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPhoneConfirmCall extends TLMethod<TLPhonePhoneCall> {
    public static final int CLASS_ID = 0x2efe1722;

    private TLInputPhoneCall peer;
    private TLBytes gA;
    private long keyFingerprint;
    private TLPhoneCallProtocol protocol;

    public TLRequestPhoneConfirmCall() {
        super();
    }

    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    public TLBytes getgA() {
        return gA;
    }

    public void setgA(TLBytes gA) {
        this.gA = gA;
    }

    public long getKeyFingerprint() {
        return keyFingerprint;
    }

    public void setKeyFingerprint(long keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TLPhoneCallProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public TLPhonePhoneCall deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLPhonePhoneCall) {
            return (TLPhonePhoneCall) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPhonePhoneCall.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeTLBytes(gA, stream);
        StreamingUtils.writeLong(keyFingerprint, stream);
        StreamingUtils.writeTLObject(protocol, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
        gA = StreamingUtils.readTLBytes(stream, context);
        keyFingerprint = StreamingUtils.readLong(stream);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
    }

    @Override
    public String toString() {
        return "phone.confirmCall#2efe1722";
    }

}
