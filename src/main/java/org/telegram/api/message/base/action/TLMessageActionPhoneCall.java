package org.telegram.api.message.base.action;

import org.telegram.api.phone.base.call.discardreason.TLAbsPhoneCallDiscardReason;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A phone call
 * messageActionPhoneCall#80e11a7f flags:# video:flags.2?true call_id:long reason:flags.0?PhoneCallDiscardReason duration:flags.1?int = MessageAction;
 */
public class TLMessageActionPhoneCall extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x80e11a7f;

    private static final int FLAG_DISCARD_REASON           = 0x00000001; // 0
    private static final int FLAG_DURATION                 = 0x00000002; // 1
    private static final int FLAG_VIDEO                    = 0x00000004; // 2

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Call ID
     */
    private long callId;
    
    /**
     * If the call has ended, the reason why it ended
     */
    private TLAbsPhoneCallDiscardReason reason;
    
    /**
     * Duration of the call in seconds
     */
    private int duration;

    public TLMessageActionPhoneCall() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean isVideo() {
        return this.isFlagSet(FLAG_VIDEO);
    }
    
    public void setVideo(boolean value) {
        this.setFlag(FLAG_VIDEO, value);
    }

    public long getCallId() {
        return callId;
    }

    public void setCallId(long callId) {
        this.callId = callId;
    }

    public boolean hasReason() {
        return this.isFlagSet(FLAG_DISCARD_REASON);
    }
    
    public TLAbsPhoneCallDiscardReason getReason() {
        return reason;
    }

    public void setReason(TLAbsPhoneCallDiscardReason reason) {
        this.reason = reason;
        if (this.duration != 0) {
            this.flags |= FLAG_DISCARD_REASON;
        } else {
            this.flags &= ~FLAG_DISCARD_REASON;
        }
    }

    public boolean hasDuration() {
        return this.isFlagSet(FLAG_DURATION);
    }
    
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        if (this.duration != 0) {
            this.flags |= FLAG_DURATION;
        } else {
            this.flags &= ~FLAG_DURATION;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(callId, stream);
        if ((flags & FLAG_DISCARD_REASON) != 0) {
            StreamingUtils.writeTLObject(reason, stream);
        }
        if ((flags & FLAG_DURATION) != 0) {
            StreamingUtils.writeInt(duration, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        callId = StreamingUtils.readLong(stream);
        if ((flags & FLAG_DISCARD_REASON) != 0) {
            reason = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCallDiscardReason.class);
        }
        if ((flags & FLAG_DURATION) != 0) {
            duration = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "messageActionPhoneCall#80e11a7f";
    }

}