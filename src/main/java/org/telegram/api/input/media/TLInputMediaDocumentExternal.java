package org.telegram.api.input.media;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media photo.
 */
public class TLInputMediaDocumentExternal extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfb52dc99;

    private static final int FLAG_TTL_SECONDS   = 0x00000001; // 0

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    private String url;
    private int ttlSeconds;

    public TLInputMediaDocumentExternal() {
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

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
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
        return "inputMediaDocumentExternal#fb52dc99";
    }

}