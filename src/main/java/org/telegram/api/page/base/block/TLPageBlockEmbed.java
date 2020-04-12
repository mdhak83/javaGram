package org.telegram.api.page.base.block;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.TLPageCaption;

/**
 * An embedded webpage
 */
public class TLPageBlockEmbed extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa8718dc5;

    private static final int FLAG_FULL_WIDTH       = 0x00000001; // 0
    private static final int FLAG_URL              = 0x00000002; // 1
    private static final int FLAG_HTML             = 0x00000004; // 2
    private static final int FLAG_ALLOW_SCROLLING  = 0x00000008; // 3
    private static final int FLAG_POSTER_PHOTO_ID  = 0x00000010; // 4
    private static final int FLAG_SIZE             = 0x00000020; // 5

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;

    private String url;
    private String html;
    private long posterPhotoId;
    private int w;
    private int h;
    private TLPageCaption caption;

    public TLPageBlockEmbed() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml() {
        return html;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public TLPageCaption getCaption() {
        return caption;
    }

    public long getPosterPhotoId() {
        return posterPhotoId;
    }

    public boolean isFullWidth() {
        return this.isFlagSet(FLAG_FULL_WIDTH);
    }

    public boolean allowScrolling() {
        return this.isFlagSet(FLAG_ALLOW_SCROLLING);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(this.url, stream);
        }
        if ((this.flags & FLAG_HTML) != 0) {
            StreamingUtils.writeTLString(this.html, stream);
        }
        if ((this.flags & FLAG_POSTER_PHOTO_ID) != 0) {
            StreamingUtils.writeLong(this.posterPhotoId, stream);
        }
        if ((this.flags & FLAG_SIZE) != 0) {
            StreamingUtils.writeInt(this.w, stream);
            StreamingUtils.writeInt(this.h, stream);
        }
        StreamingUtils.writeTLObject(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_URL) != 0) {
            this.url = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_HTML) != 0) {
            this.html = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_POSTER_PHOTO_ID) != 0) {
            this.posterPhotoId = StreamingUtils.readLong(stream);
        }
        if ((this.flags & FLAG_SIZE) != 0) {
            this.w = StreamingUtils.readInt(stream);
            this.h = StreamingUtils.readInt(stream);
        }
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
    }

    @Override
    public String toString() {
        return "pageBlockEmbed#a8718dc5";
    }
    
}
