package org.telegram.api.messages.base.stickers;

import org.telegram.api.sticker.base.set.TLStickerSet;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Info about all installed stickers
 * messages.allStickers#edfd405f hash:int sets:Vector&lt;StickerSet&gt; = messages.AllStickers;
 */
public class TLAllStickers extends TLAbsAllStickers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xedfd405f;

    /**
     * 	Hash for pagination @see <a href="https://core.telegram.org/api/offsets#hash-generation">for more info click here</a>
     */
    private int hash;
    
    /**
     * All stickersets
     */
    private TLVector<TLStickerSet> sets = new TLVector<>();

    public TLAllStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return this.hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public TLVector<TLStickerSet> getSets() {
        return this.sets;
    }

    public void setSets(TLVector<TLStickerSet> sets) {
        this.sets = sets;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLVector(this.sets, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.sets = StreamingUtils.readTLVector(stream, context, TLStickerSet.class);
    }

    @Override
    public String toString() {
        return "messages.allStickers#edfd405f";
    }

}