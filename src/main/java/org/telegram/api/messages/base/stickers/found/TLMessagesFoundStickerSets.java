package org.telegram.api.messages.base.stickers.found;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.sticker.base.stickersetcovered.TLAbsStickerSetCovered;

/**
 * Found stickersets
 * messages.foundStickerSets#5108d648 hash:int sets:Vector&lt;StickerSetCovered&gt; = messages.FoundStickerSets;
 */
public class TLMessagesFoundStickerSets extends TLAbsMessagesFoundStickerSets {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5108d648;

    /**
     * 	Hash for pagination @see <a href="https://core.telegram.org/api/offsets#hash-generation">for more info click here</a>
     */
    private int hash;
    
    /**
     * Found stickersets
     */
    private TLVector<TLAbsStickerSetCovered> sets = new TLVector<>();

    public TLMessagesFoundStickerSets() {
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

    public TLVector<TLAbsStickerSetCovered> getSets() {
        return this.sets;
    }

    public void setSets(TLVector<TLAbsStickerSetCovered> sets) {
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
        this.sets = StreamingUtils.readTLVector(stream, context, TLAbsStickerSetCovered.class);
    }

    @Override
    public String toString() {
        return "messages.foundStickerSets#5108d648";
    }

}