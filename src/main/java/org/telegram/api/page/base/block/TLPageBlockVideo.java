package org.telegram.api.page.base.block;

import org.telegram.api.richtext.base.TLAbsRichText;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.TLPageCaption;

/**
 * Video
 */
public class TLPageBlockVideo extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7c8fe7b6;

    private static final int FLAG_AUTOPLAY    = 0x00000001; // 0
    private static final int FLAG_LOOP        = 0x00000002; // 1

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;

    private long videoId;
    private TLPageCaption caption;

    public TLPageBlockVideo() {
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

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public void setCaption(TLPageCaption caption) {
        this.caption = caption;
    }

    public long getVideoId() {
        return videoId;
    }

    public TLPageCaption getCaption() {
        return caption;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.videoId, stream);
        StreamingUtils.writeTLObject(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.videoId = StreamingUtils.readLong(stream);
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
    }

    @Override
    public String toString() {
        return "pageBlockVideo#7c8fe7b6";
    }

}
