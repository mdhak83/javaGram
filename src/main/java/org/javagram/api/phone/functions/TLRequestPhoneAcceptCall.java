package org.javagram.api.phone.functions;

import org.javagram.api.phone.base.call.input.TLInputPhoneCall;
import org.javagram.api.phone.base.TLPhoneCallProtocol;
import org.javagram.api.phone.base.TLPhonePhoneCall;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.utils.StreamingUtils;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPhoneAcceptCall extends TLMethod<TLPhonePhoneCall> {
    public static final int CLASS_ID = 0x3bd2b4a0;

    private TLInputPhoneCall peer;
    private TLBytes gB;
    private TLPhoneCallProtocol protocol;

    public TLRequestPhoneAcceptCall() {
        super();
    }

    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    public TLBytes getgB() {
        return gB;
    }

    public void setgB(TLBytes gB) {
        this.gB = gB;
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
        StreamingUtils.writeTLBytes(gB, stream);
        StreamingUtils.writeTLObject(protocol, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
        gB = StreamingUtils.readTLBytes(stream, context);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
    }

    @Override
    public String toString() {
        return "phone.acceptCall#3bd2b4a0";
    }

}
