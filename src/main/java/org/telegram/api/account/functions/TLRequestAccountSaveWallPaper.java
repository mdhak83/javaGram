package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.wallpaper.base.input.TLAbsInputWallPaper;
import org.telegram.api.wallpaper.base.TLWallPaperSettings;

/**
 * Install/uninstall wallpaper
 * account.saveWallPaper#6c5a5b37 wallpaper:InputWallPaper unsave:Bool settings:WallPaperSettings = Bool;
 */
public class TLRequestAccountSaveWallPaper extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6c5a5b37;

    /**
     * Wallpaper to save
     */
    private TLAbsInputWallPaper wallpaper;
    
    /**
     * Uninstall wallpaper?
     */
    private boolean unsave;
    
    /**
     * Wallpaper settings
     */
    private TLWallPaperSettings settings;

    public TLRequestAccountSaveWallPaper() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputWallPaper getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(TLAbsInputWallPaper wallpaper) {
        this.wallpaper = wallpaper;
    }

    public boolean isUnsave() {
        return unsave;
    }

    public void setUnsave(boolean unsave) {
        this.unsave = unsave;
    }

    public TLWallPaperSettings getSettings() {
        return settings;
    }

    public void setSettings(TLWallPaperSettings settings) {
        this.settings = settings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.wallpaper, stream);
        StreamingUtils.writeTLBool(this.unsave, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.wallpaper = StreamingUtils.readTLObject(stream, context, TLAbsInputWallPaper.class);
        this.unsave = StreamingUtils.readTLBool(stream);
        this.settings = StreamingUtils.readTLObject(stream, context, TLWallPaperSettings.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.saveWallPaper#6c5a5b37";
    }

}