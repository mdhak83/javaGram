package org.javagram.api.messages.base.stickers.recent;

import org.javagram.api.document.base.TLAbsDocument;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.sticker.base.pack.TLStickerPack;
import org.javagram.api._primitives.TLIntVector;

/**
 * Recently used stickers
 */
public class TLMessagesRecentStickers extends TLObject {
    public static final int CLASS_ID = 0x22f3afb3;

    private int hash;
    private TLVector<TLStickerPack> packs;
    private TLVector<TLAbsDocument> stickers;
    private TLIntVector dates;

    public TLMessagesRecentStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getHash() {
        return hash;
    }

    public TLVector<TLAbsDocument> getStickers() {
        return stickers;
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

    public void setStickers(TLVector<TLAbsDocument> stickers) {
        this.stickers = stickers;
    }

    public TLVector<Integer> getDates() {
        return dates;
    }

    public void setDates(TLIntVector dates) {
        this.dates = dates;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.hash, stream);
        StreamingUtils.writeTLVector(this.packs, stream);
        StreamingUtils.writeTLVector(this.stickers, stream);
        StreamingUtils.writeTLVector(this.dates, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readInt(stream);
        this.packs = StreamingUtils.readTLVector(stream, context, TLStickerPack.class);
        this.stickers = StreamingUtils.readTLVector(stream, context, TLAbsDocument.class);
        this.dates = StreamingUtils.readTLIntVector(stream, context);
    }

    @Override
    public String toString() {
        return "messages.recentStickers#22f3afb3";
    }

}