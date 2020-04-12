package org.telegram.api.page.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * Related article
 * pageRelatedArticle#b390dc08 flags:# url:string webpage_id:long title:flags.0?string description:flags.1?string photo_id:flags.2?long author:flags.3?string published_date:flags.4?int = PageRelatedArticle;
 */
public class TLPageRelatedArticle extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb390dc08;

    private static final int FLAG_TITLE             = 0x00000001; // 0
    private static final int FLAG_DESCRIPTION       = 0x00000002; // 1 
    private static final int FLAG_PHOTO_ID          = 0x00000004; // 2
    private static final int FLAG_AUTHOR            = 0x00000008; // 3: 
    private static final int FLAG_PUBLISHED_DATE    = 0x00000010; // 4 

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * URL of article
     */
    private String url;
    
    /**
     * Webpage ID of generated IV preview
     */
    private long webpageId;
    
    /**
     * Title
     */
    private String title;
    
    /**
     * Description
     */
    private String description;
    
    /**
     * ID of preview photo
     */
    private long photoId;
    
    /**
     * Author name
     */
    private String author;
    
    /**
     * Date of publication
     */
    private int publishedDate;
    
    public TLPageRelatedArticle() {
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

    public boolean hasTitle() {
        return this.isFlagSet(FLAG_TITLE);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
        if (this.title != null && !this.title.trim().isEmpty()) {
            this.flags |= FLAG_TITLE;
        } else {
            this.flags &= ~FLAG_TITLE;
        }
    }

    public boolean hasDescription() {
        return this.isFlagSet(FLAG_DESCRIPTION);
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if (this.description != null && !this.description.trim().isEmpty()) {
            this.flags |= FLAG_DESCRIPTION;
        } else {
            this.flags &= ~FLAG_DESCRIPTION;
        }
    }

    public boolean hasPhotoId() {
        return this.isFlagSet(FLAG_PHOTO_ID);
    }
    
    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
        if (this.photoId != 0) {
            this.flags |= FLAG_PHOTO_ID;
        } else {
            this.flags &= ~FLAG_PHOTO_ID;
        }
    }

    public boolean hasAuthor() {
        return this.isFlagSet(FLAG_AUTHOR);
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        if (this.author != null && !this.author.trim().isEmpty()) {
            this.flags |= FLAG_AUTHOR;
        } else {
            this.flags &= ~FLAG_AUTHOR;
        }
    }

    public boolean hasPublishedDate() {
        return this.isFlagSet(FLAG_PUBLISHED_DATE);
    }
    
    public int getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(int publishedDate) {
        this.publishedDate = publishedDate;
        if (this.publishedDate != 0) {
            this.flags |= FLAG_PUBLISHED_DATE;
        } else {
            this.flags &= ~FLAG_PUBLISHED_DATE;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeLong(this.webpageId, stream);
        if (this.hasTitle()) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if (this.hasDescription()) {
            StreamingUtils.writeTLString(this.description, stream);
        }
        if (this.hasPhotoId()) {
            StreamingUtils.writeLong(this.photoId, stream);
        }
        if (this.hasAuthor()) {
            StreamingUtils.writeTLString(this.author, stream);
        }
        if (this.hasPublishedDate()) {
            StreamingUtils.writeInt(this.publishedDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.url = StreamingUtils.readTLString(stream);
        this.webpageId = StreamingUtils.readLong(stream);
        if (this.hasTitle()) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if (this.hasDescription()) {
            this.description = StreamingUtils.readTLString(stream);
        }
        if (this.hasPhotoId()) {
            this.photoId = StreamingUtils.readLong(stream);
        }
        if (this.hasAuthor()) {
            this.author = StreamingUtils.readTLString(stream);
        }
        if (this.hasPublishedDate()) {
            this.publishedDate = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "pageRelatedArticle#b390dc08";
    }

}