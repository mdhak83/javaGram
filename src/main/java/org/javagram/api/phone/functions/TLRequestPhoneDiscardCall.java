package org.javagram.api.phone.functions;

import org.javagram.api.phone.base.call.input.TLInputPhoneCall;
import org.javagram.api.phone.base.call.discardreason.TLAbsPhoneCallDiscardReason;
import org.javagram.api.updates.base.TLAbsUpdates;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.utils.StreamingUtils;

/**
 * Refuse or end running call
 * phone.discardCall#b2cbc1c0 flags:# video:flags.0?true peer:InputPhoneCall duration:int reason:PhoneCallDiscardReason connection_id:long = Updates;
 */
public class TLRequestPhoneDiscardCall extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb2cbc1c0;

    private static final int FLAG_VIDEO = 0x00000001; // 0 : Whether this is a video call

    /**
     * The phone call
     */
    private TLInputPhoneCall peer;

    /**
     * Call duration
     */
    private int duration;

    /**
     * Why was the call discarded
     */
    private TLAbsPhoneCallDiscardReason reason;

    /**
     * Preferred libtgvoip relay ID
     */
    private long connectionId;

    public TLRequestPhoneDiscardCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isVideo() {
        return this.isFlagSet(FLAG_VIDEO);
    }

    public void setVideo(boolean value) {
        this.setFlag(FLAG_VIDEO, value);
    }

    public TLInputPhoneCall getPeer() {
        return peer;
    }

    public void setPeer(TLInputPhoneCall peer) {
        this.peer = peer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TLAbsPhoneCallDiscardReason getReason() {
        return reason;
    }

    public void setReason(TLAbsPhoneCallDiscardReason reason) {
        this.reason = reason;
    }

    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.duration, stream);
        StreamingUtils.writeTLObject(this.reason, stream);
        StreamingUtils.writeLong(this.connectionId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputPhoneCall.class);
        this.duration = StreamingUtils.readInt(stream);
        this.reason = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCallDiscardReason.class);
        this.connectionId = StreamingUtils.readLong(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "phone.discardCall#b2cbc1c0";
    }

}