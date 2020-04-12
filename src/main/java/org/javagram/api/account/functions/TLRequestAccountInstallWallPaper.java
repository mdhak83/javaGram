package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.wallpaper.base.input.TLAbsInputWallPaper;
import org.javagram.api.wallpaper.base.TLWallPaperSettings;

/**
 * Install wallpaper
 * account.installWallPaper#feed5769 wallpaper:InputWallPaper settings:WallPaperSettings = Bool;
 */
public class TLRequestAccountInstallWallPaper extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfeed5769;

    /**
     * Wallpaper to save
     */
    private TLAbsInputWallPaper wallpaper;
    
    /**
     * Wallpaper settings
     */
    private TLWallPaperSettings settings;

    public TLRequestAccountInstallWallPaper() {
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

    public TLWallPaperSettings getSettings() {
        return settings;
    }

    public void setSettings(TLWallPaperSettings settings) {
        this.settings = settings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.wallpaper, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.wallpaper = StreamingUtils.readTLObject(stream, context, TLAbsInputWallPaper.class);
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
        return "account.installWallPaper#feed5769";
    }

}