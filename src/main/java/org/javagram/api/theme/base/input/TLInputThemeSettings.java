package org.javagram.api.theme.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.wallpaper.base.input.TLInputWallPaper;
import org.javagram.api.wallpaper.base.TLAbsBaseTheme;
import org.javagram.api.wallpaper.base.TLWallPaperSettings;
import org.javagram.api._primitives.TLObject;

/**
 * Theme settings
 * inputThemeSettings#bd507cd1 flags:# base_theme:BaseTheme accent_color:int message_top_color:flags.0?int message_bottom_color:flags.0?int wallpaper:flags.1?InputWallPaper wallpaper_settings:flags.1?WallPaperSettings = InputThemeSettings;
 */
public class TLInputThemeSettings extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbd507cd1;
    
    private static final int FLAG_MESSAGE_COLOR         = 0x00000001; // 0
    private static final int FLAG_WALLPAPER             = 0x00000002; // 1

    /**
     * Default theme on which this theme is based
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
    private TLInputWallPaper wallpaper;
    
    /**
     * Wallpaper settings
     */
    private TLWallPaperSettings wallpaperSettings;
    
    public TLInputThemeSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsBaseTheme getBaseTheme() {
        return baseTheme;
    }

    public void setBaseTheme(TLAbsBaseTheme baseTheme) {
        this.baseTheme = baseTheme;
    }

    public int getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(int accentColor) {
        this.accentColor = accentColor;
    }

    public boolean hasMessageColor() {
        return this.isFlagSet(FLAG_MESSAGE_COLOR);
    }
    
    public int getMessageTopColor() {
        return messageTopColor;
    }

    public void setMessageTopColor(int messageTopColor) {
        this.messageTopColor = messageTopColor;
        if (this.messageTopColor != 0) {
            this.flags |= FLAG_MESSAGE_COLOR;
        } else {
            this.flags &= ~FLAG_MESSAGE_COLOR;
        }
    }

    public int getMessageBottomColor() {
        return messageBottomColor;
    }

    public void setMessageBottomColor(int messageBottomColor) {
        this.messageBottomColor = messageBottomColor;
        if (this.messageBottomColor != 0) {
            this.flags |= FLAG_MESSAGE_COLOR;
        } else {
            this.flags &= ~FLAG_MESSAGE_COLOR;
        }
    }

    public boolean hasWallpaper() {
        return this.isFlagSet(FLAG_WALLPAPER);
    }
    
    public TLInputWallPaper getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(TLInputWallPaper wallpaper) {
        this.wallpaper = wallpaper;
        if (this.wallpaper != null) {
            this.flags |= FLAG_WALLPAPER;
        } else {
            this.flags &= ~FLAG_WALLPAPER;
        }
    }

    public TLWallPaperSettings getWallpaperSettings() {
        return wallpaperSettings;
    }

    public void setWallpaperSettings(TLWallPaperSettings wallpaperSettings) {
        this.wallpaperSettings = wallpaperSettings;
        if (this.wallpaperSettings != null) {
            this.flags |= FLAG_WALLPAPER;
        } else {
            this.flags &= ~FLAG_WALLPAPER;
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.baseTheme, stream);
        StreamingUtils.writeInt(this.accentColor, stream);
        if (this.hasMessageColor()) {
            StreamingUtils.writeInt(this.messageTopColor, stream);
            StreamingUtils.writeInt(this.messageBottomColor, stream);
        }
        if (this.hasWallpaper()) {
            StreamingUtils.writeTLObject(this.wallpaper, stream);
            StreamingUtils.writeTLObject(this.wallpaperSettings, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.baseTheme = StreamingUtils.readTLObject(stream, context, TLAbsBaseTheme.class);
        this.accentColor = StreamingUtils.readInt(stream);
        if (this.hasMessageColor()) {
            this.messageTopColor = StreamingUtils.readInt(stream);
            this.messageBottomColor = StreamingUtils.readInt(stream);
        }
        if (this.hasWallpaper()) {
            this.wallpaper = StreamingUtils.readTLObject(stream, context, TLInputWallPaper.class);
            this.wallpaperSettings = StreamingUtils.readTLObject(stream, context, TLWallPaperSettings.class);
        }
    }

    @Override
    public String toString() {
        return "inputThemeSettings#bd507cd1";
    }

}