package org.telegram.api.phone.base.call;

import org.telegram.api.phone.base.call.discardreason.TLAbsPhoneCallDiscardReason;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicates a discarded phone call
 * phoneCallDiscarded#50ca4de1 flags:# need_rating:flags.2?true need_debug:flags.3?true video:flags.5?true id:long reason:flags.0?PhoneCallDiscardReason duration:flags.1?int = PhoneCall;
 */
public class TLPhoneCallDiscarded extends TLAbsPhoneCall {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x50ca4de1;

    private static final int FLAG_DISCARD_REASON    = 0x00000001; // 0
    private static final int FLAG_DURATION          = 0x00000002; // 1
    private static final int FLAG_NEED_RATING       = 0x00000004; // 2
    private static final int FLAG_NEED_DEBUG        = 0x00000008; // 3
    private static final int FLAG_VIDEO             = 0x00000020; // 5

    /**
     * Call ID
     */
    private long id;
    
    /**
     * Why was the phone call discarded
     */
    private TLAbsPhoneCallDiscardReason reason;
    
    /**
     * Duration of the phone call in seconds
     */
    private int duration;

    public TLPhoneCallDiscarded() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasDiscardReason() {
        return this.isFlagSet(FLAG_DISCARD_REASON);
    }
    
    public TLAbsPhoneCallDiscardReason getReason() {
        return reason;
    }

    public void setReason(TLAbsPhoneCallDiscardReason reason) {
        this.reason = reason;
        if (this.reason != null) {
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
    
    public boolean needsRating() {
        return this.isFlagSet(FLAG_NEED_RATING);
    }
    
    public void setNeedsRating(boolean value) {
        this.setFlag(FLAG_NEED_RATING, value);
    }
    
    public boolean needsDebug() {
        return this.isFlagSet(FLAG_NEED_DEBUG);
    }
    
    public void setNeedsDebug(boolean value) {
        this.setFlag(FLAG_NEED_DEBUG, value);
    }
    
    /**
     * 
     * @return True if this is a video call, false otherwise
     */
    public boolean isVideo() {
        return this.isFlagSet(FLAG_VIDEO);
    }
    
    public void setVideo(boolean value) {
        this.setFlag(FLAG_VIDEO, value);
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        if (this.hasDiscardReason()) {
            StreamingUtils.writeTLObject(this.reason, stream);
        }
        if (this.hasDuration()) {
            StreamingUtils.writeInt(this.duration, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        if (this.hasDiscardReason()) {
            this.reason = StreamingUtils.readTLObject(stream, context, TLAbsPhoneCallDiscardReason.class);
        }
        if (this.hasDuration()) {
            this.duration = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "phoneCallDiscarded#50ca4de1";
    }
    
}
