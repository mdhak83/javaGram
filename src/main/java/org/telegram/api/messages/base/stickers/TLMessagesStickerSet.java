package org.telegram.api.messages.base.stickers;

import org.telegram.api.document.base.TLDocument;
import org.telegram.api.sticker.base.pack.TLStickerPack;
import org.telegram.api.sticker.base.set.TLStickerSet;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Stickerset and stickers inside it
 * messages.stickerSet#b60a24a6 set:StickerSet packs:Vector&lt;StickerPack&gt; documents:Vector&lt;Document&gt; = messages.StickerSet;
 */
public class TLMessagesStickerSet extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb60a24a6;

    /**
     * The stickerset
     */
    private TLStickerSet set;
    
    /**
     * Emoji info for stickers
     */
    private TLVector<TLStickerPack> packs;
    
    /**
     * Stickers in stickerset
     */
    private TLVector<TLDocument> documents;

    public TLMessagesStickerSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLStickerPack> getPacks() {
        return this.packs;
    }

    public void setPacks(TLVector<TLStickerPack> packs) {
        this.packs = packs;
    }

    public TLStickerSet getSet() {
        return this.set;
    }

    public void setSet(TLStickerSet set) {
        this.set = set;
    }

    public TLVector<TLDocument> getDocuments() {
        return this.documents;
    }

    public void setDocuments(TLVector<TLDocument> documents) {
        this.documents = documents;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.set, stream);
        StreamingUtils.writeTLVector(this.packs, stream);
        StreamingUtils.writeTLVector(this.documents, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.set = StreamingUtils.readTLObject(stream, context, TLStickerSet.class);
        this.packs = StreamingUtils.readTLVector(stream, context, TLStickerPack.class);
        this.documents = StreamingUtils.readTLVector(stream, context, TLDocument.class);
    }

    @Override
    public String toString() {
        return "messages.stickerSet#b60a24a6";
    }
    
}