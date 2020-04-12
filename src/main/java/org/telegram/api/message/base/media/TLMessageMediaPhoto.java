package org.telegram.api.message.base.media;

import org.telegram.api.photo.base.TLAbsPhoto;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Attached photo.
 * messageMediaPhoto#695150d7 flags:# photo:flags.0?Photo ttl_seconds:flags.2?int = MessageMedia;
 */
public class TLMessageMediaPhoto extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x695150d7;

    private static final int FLAG_PHOTO         = 0x00000001; // 0
    private static final int FLAG_TTL_SECONDS   = 0x00000004; // 2

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Photo
     */
    private TLAbsPhoto photo;

    /**
     * Time to live in seconds of self-destructing photo
    */
    private int ttlSeconds;

    public TLMessageMediaPhoto() {
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
    
    public boolean hasPhoto() {
        return this.isFlagSet(FLAG_PHOTO);        
    }

    public TLAbsPhoto getAbsPhoto() {
        return this.photo;
    }

    public void setPhoto(TLAbsPhoto photo) {
        this.photo = photo;
    }

    public boolean hasTtlSeconds() {
        return this.isFlagSet(FLAG_TTL_SECONDS);
    }
    
    public int getTtlSeconds() {
        return ttlSeconds;
    }

    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
        if (ttlSeconds != 0) {
            this.flags |= FLAG_TTL_SECONDS;
        } else {
            this.flags &= ~FLAG_TTL_SECONDS;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasPhoto()) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        if (this.hasTtlSeconds()) {
            StreamingUtils.writeInt(this.ttlSeconds, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasPhoto()) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        }
        if (this.hasTtlSeconds()) {
            this.ttlSeconds = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "messageMediaPhoto#695150d7";
    }

}