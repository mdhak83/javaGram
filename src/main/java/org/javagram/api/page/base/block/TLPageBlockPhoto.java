package org.javagram.api.page.base.block;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.page.base.TLPageCaption;

/**
 * pageBlockPhoto
 * A photo
 * pageBlockPhoto#1759c560 flags:# photo_id:long caption:PageCaption url:flags.0?string webpage_id:flags.0?long = PageBlock;
 */
public class TLPageBlockPhoto extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1759c560;

    private static final int FLAG_URL = 0x00000001; // 0

    /**
     * Photo ID
     */
    private long photoId;
    
    /**
     * Caption
     */
    private TLPageCaption caption;
    
    /**
     * HTTP URL of page the photo leads to when clicked
     */
    private String url;
    
    /**
     * ID of preview of the page the photo leads to when clicked
     */
    private long webpageId;

    public TLPageBlockPhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public long getWebpageId() {
        return webpageId;
    }

    public void setWebpageId(long webpageId) {
        this.webpageId = webpageId;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.photoId, stream);
        StreamingUtils.writeTLObject(this.caption, stream);
        if ((this.flags & FLAG_URL) != 0) {
            StreamingUtils.writeTLString(this.url, stream);
            StreamingUtils.writeLong(this.webpageId, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.photoId = StreamingUtils.readLong(stream);
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
        if ((this.flags & FLAG_URL) != 0) {
            this.url = StreamingUtils.readTLString(stream);
            this.webpageId = StreamingUtils.readLong(stream);
        }
    }

    @Override
    public String toString() {
        return "pageBlockPhoto#1759c560";
    }

}
