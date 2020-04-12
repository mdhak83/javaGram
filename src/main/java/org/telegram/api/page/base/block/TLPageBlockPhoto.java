package org.telegram.api.page.base.block;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.page.base.TLPageCaption;

/**
 * A photo
 */
public class TLPageBlockPhoto extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1759c560;

    private static final int FLAG_URL = 0x00000001; // 0

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;

    private long photoId;
    private TLPageCaption caption;
    private String url;
    private String webpageId;

    public TLPageBlockPhoto() {
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

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public TLPageCaption getCaption() {
        return caption;
    }

    public void setCaption(TLPageCaption caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebpageId() {
        return webpageId;
    }

    public void setWebpageId(String webpageId) {
        this.webpageId = webpageId;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.photoId, stream);
        StreamingUtils.writeTLObject(this.caption, stream);
        if ((this.flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(this.url, stream);
            StreamingUtils.writeTLString(this.webpageId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.photoId = StreamingUtils.readLong(stream);
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
        if ((this.flags & FLAG_URL) != 0) {
            this.url = StreamingUtils.readTLString(stream);
            this.webpageId = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "pageBlockPhoto#1759c560";
    }

}
