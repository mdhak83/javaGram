package org.javagram.api.stickers.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.document.base.input.TLAbsInputDocument;
import org.javagram.api.messages.base.stickers.TLMessagesStickerSet;

/**
 * Remove a sticker from the set where it belongs, bots only. The sticker set must have been created by the bot.
 * stickers.removeStickerFromSet#f7760f51 sticker:InputDocument = messages.StickerSet;
 */
public class TLRequestStickersRemoveStickerFromSet extends TLMethod<TLMessagesStickerSet> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf7760f51;

    /**
     * The sticker to remove
     */
    private TLAbsInputDocument sticker;
    
    public TLRequestStickersRemoveStickerFromSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputDocument getSticker() {
        return sticker;
    }

    public void setSticker(TLAbsInputDocument sticker) {
        this.sticker = sticker;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.sticker, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.sticker = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
    }

    @Override
    public TLMessagesStickerSet deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesStickerSet) {
            return (TLMessagesStickerSet) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "stickers.removeStickerFromSet#f7760f51";
    }

}