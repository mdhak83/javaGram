package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.file.base.input.TLAbsInputFile;
import org.javagram.api.wallpaper.base.TLAbsWallPaper;
import org.javagram.api.wallpaper.base.TLWallPaperSettings;

/**
 * Create and upload a new wallpaper
 * account.uploadWallPaper#dd853661 file:InputFile mime_type:string settings:WallPaperSettings = WallPaper;
 */
public class TLRequestAccountUploadWallPaper extends TLMethod<TLAbsWallPaper> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdd853661;

    /**
     * The JPG/PNG wallpaper
     */
    private TLAbsInputFile file;
    
    /**
     * MIME type of uploaded wallpaper
     */
    private String mimeType;
    
    /**
     * Wallpaper settings
     */
    private TLWallPaperSettings settings;

    public TLRequestAccountUploadWallPaper() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputFile getFile() {
        return file;
    }

    public void setFile(TLAbsInputFile file) {
        this.file = file;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public TLWallPaperSettings getSettings() {
        return settings;
    }

    public void setSettings(TLWallPaperSettings settings) {
        this.settings = settings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.file, stream);
        StreamingUtils.writeTLString(this.mimeType, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.file = StreamingUtils.readTLObject(stream, context, TLAbsInputFile.class);
        this.mimeType = StreamingUtils.readTLString(stream);
        this.settings = StreamingUtils.readTLObject(stream, context, TLWallPaperSettings.class);
    }

    @Override
    public TLAbsWallPaper deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsWallPaper) {
            return (TLAbsWallPaper) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.uploadWallPaper#dd853661";
    }

}