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
import org.telegram.api.wallpaper.base.TLAbsWallPaper;

/**
 * Get info about a certain wallpaper
 * account.getWallPaper#fc8ddbea wallpaper:InputWallPaper = WallPaper;
 */
public class TLRequestAccountGetWallPaper extends TLMethod<TLAbsWallPaper> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfc8ddbea;

    /**
     * The wallpaper to get info about
     */
    private TLAbsInputWallPaper wallpaper;

    public TLRequestAccountGetWallPaper() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.wallpaper, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.wallpaper = StreamingUtils.readTLObject(stream, context, TLAbsInputWallPaper.class);
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
        return "account.getWallPaper#fc8ddbea";
    }

}