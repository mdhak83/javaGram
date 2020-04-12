package org.telegram.api.theme.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Theme by theme ID
 * inputThemeSlug#f5890df1 slug:string = InputTheme;
 */
public class TLInputThemeSlug extends TLAbsInputTheme {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf5890df1;
    
    /**
     * Unique theme ID
     */
    private String slug;
    
    public TLInputThemeSlug() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.slug, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.slug = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputThemeSlug#f5890df1";
    }

}