package org.telegram.api.wallpaper.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * Webpage attributes
 */
public class TLThemeSettings extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9c14984a;

    private static final int FLAG_MESSAGE    = 0x00000001; // 0
    private static final int FLAG_WALLPAPER  = 0x00000002; // 1

    /**
     * Base theme
     */
    private TLAbsBaseTheme baseTheme;
    
    /**
     * Accent color, RGB24 format

     */
    private int accentColor;
    
    /**
     * Message gradient color (top), RGB24 format
     */
    private int messageTopColor;
    
    /**
     * Message gradient color (bottom), RGB24 format
     */
    private int messageBottomColor;
    
    /**
     * Wallpaper
     */
    private TLAbsWallPaper wallpaper;

    public TLThemeSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.baseTheme, stream);
        StreamingUtils.writeInt(this.accentColor, stream);
        if ((this.flags & FLAG_MESSAGE) != 0) {
            StreamingUtils.writeInt(this.messageTopColor, stream);
            StreamingUtils.writeInt(this.messageBottomColor, stream);
        }
        if ((this.flags & FLAG_WALLPAPER) != 0) {
            StreamingUtils.writeTLObject(this.wallpaper, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.baseTheme = StreamingUtils.readTLObject(stream, context, TLAbsBaseTheme.class);
        this.accentColor = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_MESSAGE) != 0) {
            this.messageTopColor = StreamingUtils.readInt(stream);
            this.messageBottomColor = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_WALLPAPER) != 0) {
            this.wallpaper = StreamingUtils.readTLObject(stream, context, TLAbsWallPaper.class);
        }
    }

    @Override
    public String toString() {
        return "themeSettings#9c14984a";
    }

}