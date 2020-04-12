package org.telegram.api.wallpaper.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Wallpaper by slug (a unique ID)
 * inputWallPaperSlug#72091c80 slug:string = InputWallPaper;
 */
public class TLInputWallPaperSlug extends TLAbsInputWallPaper {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x72091c80;

    /**
     * Unique wallpaper ID
     */
    private String slug;
    
    public TLInputWallPaperSlug() {
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
        return "inputWallPaperSlug#72091c80";
    }

}