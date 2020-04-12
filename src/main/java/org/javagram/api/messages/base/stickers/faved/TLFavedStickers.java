package org.javagram.api.messages.base.stickers.faved;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.api.messages.base.stickers.TLAbsAllStickers;
import org.javagram.api.sticker.base.pack.TLStickerPack;

/**
 * Favorited stickers
 * messages.favedStickers#f37f2f16 hash:int packs:Vector&lt;StickerPack&gt; stickers:Vector&lt;Document&gt; = messages.FavedStickers;
 */
public class TLFavedStickers extends TLAbsAllStickers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf37f2f16;

    /**
     * @see <a href="https://core.telegram.org/api/offsets#hash-generation">Hash for pagination, for more info click here</a>
     */
    private int hash;
    
    /**
     * Emojis associated to stickers
     */
    private TLVector<TLStickerPack> packs;
    
    /**
     * Favorited stickers
     */
    private TLVector<TLAbsDocument> stickers;

    public TLFavedStickers() {
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

    public TLVector<TLStickerPack> getPacks() {
        return packs;
    }

    public void setPacks(TLVector<TLStickerPack> packs) {
        this.packs = packs;
    }

    public TLVector<TLAbsDocument> getStickers() {
        return stickers;
    }

    public void setStickers(TLVector<TLAbsDocument> stickers) {
        this.stickers = stickers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLVector(this.packs, stream);
        StreamingUtils.writeTLVector(this.stickers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.packs = StreamingUtils.readTLVector(stream, context, TLStickerPack.class);
        this.stickers = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
    }

    @Override
    public String toString() {
        return "messages.favedStickers#f37f2f16";
    }

}