package org.javagram.api.webpage.base;

import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.api.page.base.TLAbsPage;
import org.javagram.api.photo.base.TLAbsPhoto;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLVector;

/**
 * Webpage preview
 */
public class TLWebPage extends TLAbsWebPage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe89c45b2;

    private static final int FLAG_TYPE              = 0x00000001; // 0
    private static final int FLAG_SITE_NAME         = 0x00000002; // 1
    private static final int FLAG_TITLE             = 0x00000004; // 2
    private static final int FLAG_DESCRIPTION       = 0x00000008; // 3
    private static final int FLAG_PHOTO             = 0x00000010; // 4
    private static final int FLAG_EMBED_URL         = 0x00000020; // 5
    private static final int FLAG_EMBED_SIZE        = 0x00000040; // 6
    private static final int FLAG_DURATION          = 0x00000080; // 7
    private static final int FLAG_AUTHOR            = 0x00000100; // 8
    private static final int FLAG_DOCUMENT          = 0x00000200; // 9
    private static final int FLAG_CACHED_PAGE       = 0x00000400; // 10
    private static final int FLAG_ATTRIBUTES        = 0x00001000; // 12

    /**
     * Preview ID
     */
    private long id;
    
    /**
     * URL of previewed webpage
     */
    private String url;
    
    /**
     * Webpage URL to be displayed to the user
     */
    private String displayUrl;
    
    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    
    /**
     * Type of the web page. Can be: article, photo, audio, video, document, profile, app, or something else
     */
    private String type;
    
    /**
     * Short name of the site (e.g., Google Docs, App Store)
     */
    private String siteName;
    
    /**
     * Title of the content
     */
    private String title;
    
    /**
     * Content description
     */
    private String description;
    
    /**
     * Image representing the content
     */
    private TLAbsPhoto photo;
    
    /**
     * URL to show in the embedded preview
     */
    private String embedUrl;
    
    /**
     * MIME type of the embedded preview, (e.g., text/html or video/mp4)
     */
    private String embedType;
    
    /**
     * Width of the embedded preview
     */
    private int embedWidth;
    
    /**
     * Height of the embedded preview
     */
    private int embedHeight;
    
    /**
     * Duration of the content, in seconds
     */
    private int duration;
    
    /**
     * Author of the content
     */
    private String author;
    
    /**
     * Preview of the content as a media file
     */
    private TLAbsDocument document;
    
    /**
     * Page contents in @see <a href="https://instantview.telegram.org/">instant view</a> format
     */
    private TLAbsPage cachedPage;
    
    /**
     * Webpage attributes
     */
    private TLVector<TLWebPageAttributeTheme> attributes;

    public TLWebPage() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public boolean hasType() {
        return this.isFlagSet(FLAG_TYPE);
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean hasSiteName() {
        return this.isFlagSet(FLAG_SITE_NAME);
    }
    
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public boolean hasTitle() {
        return this.isFlagSet(FLAG_TITLE);
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasDescription() {
        return this.isFlagSet(FLAG_DESCRIPTION);
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasPhoto() {
        return this.isFlagSet(FLAG_PHOTO);
    }
    
    public TLAbsPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(TLAbsPhoto photo) {
        this.photo = photo;
    }

    public boolean hasEmbedUrl() {
        return this.isFlagSet(FLAG_EMBED_URL);
    }
    
    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getEmbedType() {
        return embedType;
    }

    public void setEmbedType(String embedType) {
        this.embedType = embedType;
    }

    public boolean hasEmbedSize() {
        return this.isFlagSet(FLAG_EMBED_SIZE);
    }
    
    public int getEmbedWidth() {
        return embedWidth;
    }

    public void setEmbedWidth(int embedWidth) {
        this.embedWidth = embedWidth;
    }

    public int getEmbedHeight() {
        return embedHeight;
    }

    public void setEmbedHeight(int embedHeight) {
        this.embedHeight = embedHeight;
    }

    public boolean hasDuration() {
        return this.isFlagSet(FLAG_DURATION);
    }
    
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean hasAuthor() {
        return this.isFlagSet(FLAG_AUTHOR);
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean hasDocument() {
        return this.isFlagSet(FLAG_DOCUMENT);
    }
    
    public TLAbsDocument getDocument() {
        return document;
    }

    public void setDocument(TLAbsDocument document) {
        this.document = document;
    }

    public boolean hasCachedPage() {
        return this.isFlagSet(FLAG_CACHED_PAGE);
    }
    
    public TLAbsPage getCachedPage() {
        return cachedPage;
    }

    public void setCachedPage(TLAbsPage cachedPage) {
        this.cachedPage = cachedPage;
    }

    public boolean hasAttributes() {
        return this.isFlagSet(FLAG_ATTRIBUTES);
    }
    
    public TLVector<TLWebPageAttributeTheme> getAttributes() {
        return attributes;
    }

    public void setAttributes(TLVector<TLWebPageAttributeTheme> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeTLString(this.url, stream);
        StreamingUtils.writeTLString(this.displayUrl, stream);
        StreamingUtils.writeInt(this.hash, stream);
        if (this.hasType()) {
            StreamingUtils.writeTLString(this.type, stream);
        }
        if (this.hasSiteName()) {
            StreamingUtils.writeTLString(this.siteName, stream);
        }
        if (this.hasTitle()) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if (this.hasDescription()) {
            StreamingUtils.writeTLString(this.description, stream);
        }
        if (this.hasPhoto()) {
            StreamingUtils.writeTLObject(this.photo, stream);
        }
        if (this.hasEmbedUrl()) {
            StreamingUtils.writeTLString(this.embedUrl, stream);
            StreamingUtils.writeTLString(this.embedType, stream);
        }
        if (this.hasEmbedSize()) {
            StreamingUtils.writeInt(this.embedWidth, stream);
            StreamingUtils.writeInt(this.embedHeight, stream);
        }
        if (this.hasDuration()) {
            StreamingUtils.writeInt(this.duration, stream);
        }
        if (this.hasAuthor()) {
            StreamingUtils.writeTLString(this.author, stream);
        }
        if (this.hasDocument()) {
            StreamingUtils.writeTLObject(this.document, stream);
        }
        if (this.hasCachedPage()) {
            StreamingUtils.writeTLObject(this.cachedPage, stream);
        }
        if (this.hasAttributes()) {
            StreamingUtils.writeTLObject(this.attributes, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.url = StreamingUtils.readTLString(stream);
        this.displayUrl = StreamingUtils.readTLString(stream);
        this.hash = StreamingUtils.readInt(stream);
        if (this.hasType()) {
            this.type = StreamingUtils.readTLString(stream);
        }
        if (this.hasSiteName()) {
            this.siteName = StreamingUtils.readTLString(stream);
        }
        if (this.hasTitle()) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if (this.hasDescription()) {
            this.description = StreamingUtils.readTLString(stream);
        }
        if (this.hasPhoto()) {
            this.photo = StreamingUtils.readTLObject(stream, context, TLAbsPhoto.class);
        }
        if (this.hasEmbedUrl()) {
            this.embedUrl = StreamingUtils.readTLString(stream);
            this.embedType = StreamingUtils.readTLString(stream);
        }
        if (this.hasEmbedSize()) {
            this.embedWidth = StreamingUtils.readInt(stream);
            this.embedHeight = StreamingUtils.readInt(stream);
        }
        if (this.hasDuration()) {
            this.duration = StreamingUtils.readInt(stream);
        }
        if (this.hasAuthor()) {
            this.author = StreamingUtils.readTLString(stream);
        }
        if (this.hasDocument()) {
            this.document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
        }
        if (this.hasCachedPage()) {
            this.cachedPage = StreamingUtils.readTLObject(stream, context, TLAbsPage.class);
        }
        if (this.hasAttributes()) {
            this.attributes = StreamingUtils.readTLVector(stream, context, TLWebPageAttributeTheme.class);
        }
    }

    @Override
    public String toString() {
        return "webPage#e89c45b2";
    }

}