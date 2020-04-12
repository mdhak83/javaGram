package org.telegram.api.wallpaper.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.document.base.TLAbsDocument;

/**
 * Wallpaper settings.
 * wallPaper#a437c3ed id:long flags:# creator:flags.0?true default:flags.1?true pattern:flags.3?true dark:flags.4?true access_hash:long slug:string document:Document settings:flags.2?WallPaperSettings = WallPaper;
 */
public class TLWallPaper extends TLAbsWallPaper {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa437c3ed;

    private static final int FLAG_CREATOR   = 0x00000001; // 0 : Creator of the wallpaper
    private static final int FLAG_DEFAULT   = 0x00000002; // 1 : Whether this is the default wallpaper
    private static final int FLAG_SETTINGS  = 0x00000004; // 2 : Wallpaper settings
    private static final int FLAG_PATTERN   = 0x00000008; // 3 : Pattern
    private static final int FLAG_DARK      = 0x00000010; // 4 : Dark mode

    /**
     * Identifier
     */
    private long id;

    /**
     * Access hash
     */
    private long accessHash;

    /**
     * Unique wallpaper ID
     */
    private String slug;

    /**
     * The actual wallpaper
     */
    private TLAbsDocument document;

    /**
     * Wallpaper settings
     */
    private TLWallPaperSettings settings;

    public TLWallPaper() {
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

    public boolean isCreator() {
        return this.isFlagSet(FLAG_CREATOR);
    }

    public void setCreator(boolean value) {
        this.setFlag(FLAG_CREATOR, value);
    }

    public boolean isDefault() {
        return this.isFlagSet(FLAG_DEFAULT);
    }

    public void setDefault(boolean value) {
        this.setFlag(FLAG_DEFAULT, value);
    }

    public boolean isPattern() {
        return this.isFlagSet(FLAG_PATTERN);
    }

    public void setPattern(boolean value) {
        this.setFlag(FLAG_PATTERN, value);
    }

    public boolean isDark() {
        return this.isFlagSet(FLAG_DARK);
    }

    public void setDark(boolean value) {
        this.setFlag(FLAG_DARK, value);
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public TLAbsDocument getDocument() {
        return document;
    }

    public void setDocument(TLAbsDocument document) {
        this.document = document;
    }
    
    public boolean hasSettings() {
        return this.isFlagSet(FLAG_SETTINGS);
    }

    public TLWallPaperSettings getSettings() {
        return settings;
    }

    public void setSettings(TLWallPaperSettings settings) {
        this.settings = settings;
        if (settings != null) {
            this.flags |= FLAG_SETTINGS;
        } else {
            this.flags &= ~FLAG_SETTINGS;
        }
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLString(this.slug, stream);
        StreamingUtils.writeTLObject(this.document, stream);
        if ((this.flags & FLAG_SETTINGS) != 0) {
            StreamingUtils.writeTLObject(this.settings, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.flags = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.slug = StreamingUtils.readTLString(stream);
        this.document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
        if ((this.flags & FLAG_SETTINGS) != 0) {
            this.settings = StreamingUtils.readTLObject(stream, context, TLWallPaperSettings.class);
        }
    }

    @Override
    public String toString() {
        return "wallPaper#a437c3ed";
    }

}