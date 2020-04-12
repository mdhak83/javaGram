package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.document.base.input.TLAbsInputDocument;
import org.telegram.api.theme.base.TLTheme;
import org.telegram.api.theme.base.input.TLAbsInputTheme;
import org.telegram.api.theme.base.input.TLInputThemeSettings;

/**
 * Update theme
 * account.updateTheme#5cb367d5 flags:# format:string theme:InputTheme slug:flags.0?string title:flags.1?string document:flags.2?InputDocument settings:flags.3?InputThemeSettings = Theme;
 */
public class TLRequestAccountUpdateTheme extends TLMethod<TLTheme> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5cb367d5;

    private static final int FLAG_SLUG      = 0x00000001; // 0 : unique theme ID
    private static final int FLAG_TITLE     = 0x00000002; // 1 : Theme name
    private static final int FLAG_DOCUMENT  = 0x00000004; // 2 : Theme file
    private static final int FLAG_SETTINGS  = 0x00000008; // 3 : Theme settings

    /**
     * Theme format, a string that identifies the theming engines supported by the client
     */
    private String format;
    
    /**
     * Theme to update
     */
    private TLAbsInputTheme theme;

    /**
     * Unique theme ID
     */
    private String slug;
    
    /**
     * Theme name
     */
    private String title;
    
    /**
     * Theme file
     */
    private TLAbsInputDocument document;
    
    /**
     * Theme settings
     */
    private TLInputThemeSettings settings;
    
    public TLRequestAccountUpdateTheme() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public TLAbsInputTheme getTheme() {
        return theme;
    }

    public void setTheme(TLAbsInputTheme theme) {
        this.theme = theme;
    }

    public boolean hasSlug() {
        return this.isFlagSet(FLAG_SLUG);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
        this.setFlag(FLAG_SLUG, this.slug != null && !this.slug.trim().isEmpty());
    }

    public boolean hasTitle() {
        return this.isFlagSet(FLAG_TITLE);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.setFlag(FLAG_TITLE, this.title != null && !this.title.trim().isEmpty());
    }

    public boolean hasDocument() {
        return this.isFlagSet(FLAG_DOCUMENT);
    }

    public TLAbsInputDocument getDocument() {
        return document;
    }

    public void setDocument(TLAbsInputDocument document) {
        this.document = document;
        this.setFlag(FLAG_DOCUMENT, this.document != null);
    }

    public boolean hasSettings() {
        return this.isFlagSet(FLAG_SETTINGS);
    }

    public TLInputThemeSettings getSettings() {
        return settings;
    }

    public void setSettings(TLInputThemeSettings settings) {
        this.settings = settings;
        this.setFlag(FLAG_SETTINGS, this.settings != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.format, stream);
        StreamingUtils.writeTLObject(this.theme, stream);
        if (this.hasSlug()) {
            StreamingUtils.writeTLString(this.slug, stream);
        }
        if (this.hasTitle()) {
            StreamingUtils.writeTLString(this.title, stream);
        }
        if (this.hasDocument()) {
            StreamingUtils.writeTLObject(this.document, stream);
        }
        if (this.hasSettings()) {
            StreamingUtils.writeTLObject(this.settings, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.format = StreamingUtils.readTLString(stream);
        this.theme = StreamingUtils.readTLObject(stream, context, TLAbsInputTheme.class);
        if (this.hasSlug()) {
            this.slug = StreamingUtils.readTLString(stream);
        }
        if (this.hasTitle()) {
            this.title = StreamingUtils.readTLString(stream);
        }
        if (this.hasDocument()) {
            this.document = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
        }
        if (this.hasSettings()) {
            this.settings = StreamingUtils.readTLObject(stream, context, TLInputThemeSettings.class);
        }
    }

    @Override
    public TLTheme deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLTheme) {
            return (TLTheme) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.updateTheme#5cb367d5";
    }

}