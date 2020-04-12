package org.telegram.api.wallpaper.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * No file wallpaper
 * wallPaperNoFile#8af40b25 flags:# default:flags.1?true dark:flags.4?true settings:flags.2?WallPaperSettings = WallPaper;
 */
public class TLWallPaperNoFile extends TLAbsWallPaper {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8af40b25;

    private static final int FLAG_DEFAULT   = 0x00000002; // 1 : Whether this is the default wallpaper
    private static final int FLAG_SETTINGS  = 0x00000004; // 2 : Wallpaper settings
    private static final int FLAG_DARK      = 0x00000010; // 4 : Dark mode

    /**
     * Wallpaper settings
     */
    private TLWallPaperSettings settings;

    public TLWallPaperNoFile() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isDefault() {
        return this.isFlagSet(FLAG_DEFAULT);
    }

    public void setDefault(boolean value) {
        this.setFlag(FLAG_DEFAULT, value);
    }

    public boolean isDark() {
        return this.isFlagSet(FLAG_DARK);
    }

    public void setDark(boolean value) {
        this.setFlag(FLAG_DARK, value);
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
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasSettings()) {
            StreamingUtils.writeTLObject(this.settings, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasSettings()) {
            this.settings = StreamingUtils.readTLObject(stream, context, TLWallPaperSettings.class);
        }
    }

    @Override
    public String toString() {
        return "wallPaperNoFile#8af40b25";
    }

}