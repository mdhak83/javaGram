package org.javagram.api.input.media;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * New photo that will be uploaded by the server using the specified URL
 */
public class TLInputMediaPhotoExternal extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe5bbfe1a;

    private static final int FLAG_TTL_SECONDS   = 0x00000001; // 0

    private String url;
    private int ttlSeconds;

    public TLInputMediaPhotoExternal() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTtlSeconds() {
        return ttlSeconds;
    }

    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.url, stream);
        if ((this.flags & FLAG_TTL_SECONDS) != 0) {
            StreamingUtils.writeInt(this.ttlSeconds, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.url = StreamingUtils.readTLString(stream);
        if ((this.flags & FLAG_TTL_SECONDS) != 0) {
            this.ttlSeconds = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "inputMediaPhotoExternal#e5bbfe1a";
    }
    
}