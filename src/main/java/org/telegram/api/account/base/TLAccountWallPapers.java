package org.telegram.api.account.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.wallpaper.base.TLAbsWallPaper;
import org.telegram.api._primitives.TLVector;

/**
 * Installed wallpapers
 * account.wallPapers#702b65a9 hash:int wallpapers:Vector&lt;WallPaper&gt; = account.WallPapers;
 */
public class TLAccountWallPapers extends TLAbsAccountWallPapers {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x702b65a9;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    
    /**
     * Wallpapers
     */
    private TLVector<TLAbsWallPaper> wallpapers;
    
    public TLAccountWallPapers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public TLVector<TLAbsWallPaper> getWallpapers() {
        return wallpapers;
    }

    public void setWallpapers(TLVector<TLAbsWallPaper> wallpapers) {
        this.wallpapers = wallpapers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLVector(this.wallpapers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.wallpapers = StreamingUtils.readTLVector(stream, context, TLAbsWallPaper.class);
    }

    @Override
    public String toString() {
        return "account.wallPapers#702b65a9";
    }

}