package org.telegram.api.phone.functions;

import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.api.phone.base.TLPhoneCallProtocol;
import org.telegram.api.phone.base.TLPhonePhoneCall;
import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * Start a telegram phone call
 * phone.requestCall#42ff96ed flags:# video:flags.0?true user_id:InputUser random_id:int g_a_hash:bytes protocol:PhoneCallProtocol = phone.PhoneCall;
 */
public class TLRequestPhoneRequestCall extends TLMethod<TLPhonePhoneCall> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x42ff96ed;

    private static final int FLAG_VIDEO = 0x00000001; // 0 : Whether to start a video call

    /**
     * Destination of the phone call
     */
    private TLAbsInputUser userId;

    /**
     * Random ID to avoid resending the same object
     */
    private int randomId;

    /**
     * @see <a href="https://core.telegram.org/api/end-to-end/voice-calls">Parameter for E2E encryption key exchange »</a>
     */
    private TLBytes gAHash;

    /**
     * Phone call settings
     */
    private TLPhoneCallProtocol protocol;

    public TLRequestPhoneRequestCall() {
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

    public TLAbsInputUser getUserId() {
        return userId;
    }

    public void setUserId(TLAbsInputUser userId) {
        this.userId = userId;
    }

    public int getRandomId() {
        return randomId;
    }

    public void setRandomId(int randomId) {
        this.randomId = randomId;
    }

    public TLBytes getgAHash() {
        return gAHash;
    }

    public void setgAHash(TLBytes gAHash) {
        this.gAHash = gAHash;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TLPhoneCallProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.userId, stream);
        StreamingUtils.writeInt(this.randomId, stream);
        StreamingUtils.writeTLBytes(this.gAHash, stream);
        StreamingUtils.writeTLObject(this.protocol, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.randomId = StreamingUtils.readInt(stream);
        this.gAHash = StreamingUtils.readTLBytes(stream, context);
        this.protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
    }

    @Override
    public TLPhonePhoneCall deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLPhonePhoneCall) {
            return (TLPhonePhoneCall) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLPhonePhoneCall.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "phone.requestCall#42ff96ed";
    }

}