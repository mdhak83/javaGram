package org.telegram.api.theme.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.document.base.TLAbsDocument;
import org.telegram.api.wallpaper.base.TLThemeSettings;
import org.telegram.api._primitives.TLObject;

/**
 * Theme
 * theme#28f1114 flags:# creator:flags.0?true default:flags.1?true id:long access_hash:long slug:string title:string document:flags.2?Document settings:flags.3?ThemeSettings installs_count:int = Theme;
 */
public class TLTheme extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x28f1114;
    
    private static final int FLAG_CREATOR   = 0x00000001; // 0 : Whether the current user is the creator of this theme
    private static final int FLAG_DEFAULT   = 0x00000002; // 1 : Whether this is the default theme
    private static final int FLAG_DOCUMENT  = 0x00000004; // 2 : Theme
    private static final int FLAG_SETTINGS  = 0x00000008; // 3 : Theme settings

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * Theme ID
     */
    private long id;
    
    /**
     * Theme access hash
     */
    private long accessHash;
    
    /**
     * Unique theme ID
     */
    private String slug;
    
    /**
     * Theme name
     */
    private String title;
    
    /**
     * Theme
     */
    private TLAbsDocument document;
    
    /**
     * Theme settings
     */
    private TLThemeSettings settings;
    
    /**
     * Installation count
     */
    private int installsCount;

    public TLTheme() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasDocument() {
        return this.isFlagSet(FLAG_DOCUMENT);
    }
    
    public TLAbsDocument getDocument() {
        return document;
    }

    public void setDocument(TLAbsDocument document) {
        this.document = document;
        if (this.document != null) {
            this.flags |= FLAG_DOCUMENT;
        } else {
            this.flags &= ~FLAG_DOCUMENT;
        }
    }

    public boolean hasSettings() {
        return this.isFlagSet(FLAG_SETTINGS);
    }
    
    public TLThemeSettings getSettings() {
        return settings;
    }

    public void setSettings(TLThemeSettings settings) {
        this.settings = settings;
        if (this.settings != null) {
            this.flags |= FLAG_SETTINGS;
        } else {
            this.flags &= ~FLAG_SETTINGS;
        }
    }

    public int getInstallsCount() {
        return installsCount;
    }

    public void setInstallsCount(int installsCount) {
        this.installsCount = installsCount;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLString(this.slug, stream);
        StreamingUtils.writeTLString(this.title, stream);
        if (this.hasDocument()) {
            StreamingUtils.writeTLObject(this.document, stream);
        }
        if (this.hasSettings()) {
            StreamingUtils.writeTLObject(this.settings, stream);
        }
        StreamingUtils.writeInt(this.installsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.slug = StreamingUtils.readTLString(stream);
        this.title = StreamingUtils.readTLString(stream);
        if (this.hasDocument()) {
            this.document = StreamingUtils.readTLObject(stream, context, TLAbsDocument.class);
        }
        if (this.hasSettings()) {
            this.settings = StreamingUtils.readTLObject(stream, context, TLThemeSettings.class);
        }
        this.installsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "theme#28f1114";
    }

}