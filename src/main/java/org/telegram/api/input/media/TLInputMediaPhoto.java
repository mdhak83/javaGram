package org.telegram.api.input.media;

import org.telegram.api.photo.base.input.TLAbsInputPhoto;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media photo.
 */
public class TLInputMediaPhoto extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb3ba0635;

    private static final int FLAG_TTL_SECONDS = 0x00000001; // 0

    private TLAbsInputPhoto id;
    private int ttlSeconds;

    public TLInputMediaPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPhoto getId() {
        return this.id;
    }

    public void setId(TLAbsInputPhoto value) {
        this.id = value;
    }

    public boolean hasTtlSeconds() {
        return this.isFlagSet(FLAG_TTL_SECONDS);
    }
    
    public int getTtlSeconds() {
        return this.ttlSeconds;
    }

    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
        this.setFlag(FLAG_TTL_SECONDS, this.ttlSeconds != 0);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.id, stream);
        if (this.hasTtlSeconds()) {
            StreamingUtils.writeInt(this.ttlSeconds, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readTLObject(stream, context, TLAbsInputPhoto.class);
        if (this.hasTtlSeconds()) {
            this.ttlSeconds = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "inputMediaPhoto#b3ba0635";
    }

}