package org.telegram.api.account.functions;

import org.telegram.api.wallpaper.base.TLAbsWallPaper;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns a list of available wallpapers.
 * account.getWallPapers#aabb1763 hash:int = account.WallPapers;
 */
public class TLRequestAccountGetWallPapers extends TLMethod<TLVector<TLAbsWallPaper>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaabb1763;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    

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
    

    @Override
    public TLVector<TLAbsWallPaper> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        return StreamingUtils.readTLVector(stream, context, TLAbsWallPaper.class);
    }

    @Override
    public String toString() {
        return "account.getWallPapers#aabb1763";
    }

}