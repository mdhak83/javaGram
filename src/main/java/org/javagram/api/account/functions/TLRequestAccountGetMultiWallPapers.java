package org.javagram.api.account.functions;

import org.javagram.api.wallpaper.base.TLAbsWallPaper;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.wallpaper.base.input.TLAbsInputWallPaper;

/**
 * Get info about multiple wallpapers
 * account.getMultiWallPapers#65ad71dc wallpapers:Vector&lt;InputWallPaper&gt; = Vector&lt;WallPaper&gt;;
 */
public class TLRequestAccountGetMultiWallPapers extends TLMethod<TLVector<TLAbsWallPaper>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x65ad71dc;

    /**
     * Wallpapers to fetch info about
     */
    private TLVector<TLAbsInputWallPaper> wallpapers;
    

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsInputWallPaper> getWallpapers() {
        return wallpapers;
    }

    public void setWallpapers(TLVector<TLAbsInputWallPaper> wallpapers) {
        this.wallpapers = wallpapers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.wallpapers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.wallpapers = StreamingUtils.readTLVector(stream, context, TLAbsInputWallPaper.class);
    }

    @Override
    public TLVector<TLAbsWallPaper> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLAbsWallPaper.class);
    }

    @Override
    public String toString() {
        return "account.getMultiWallPapers#65ad71dc";
    }

}