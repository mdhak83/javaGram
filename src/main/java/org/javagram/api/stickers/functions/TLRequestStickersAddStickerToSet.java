package org.javagram.api.stickers.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.messages.base.stickers.TLMessagesStickerSet;
import org.javagram.api.sticker.base.input.set.TLAbsInputStickerSet;
import org.javagram.api.sticker.base.input.set.TLInputStickerSetItem;

/**
 * Add a sticker to a stickerset, bots only. The sticker set must have been created by the bot.
 * stickers.addStickerToSet#8653febe stickerset:InputStickerSet sticker:InputStickerSetItem = messages.StickerSet;
 */
public class TLRequestStickersAddStickerToSet extends TLMethod<TLMessagesStickerSet> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8653febe;

    /**
     * The stickerset
     */
    private TLAbsInputStickerSet stickerset;
    
    /**
     * The sticker
     */
    private TLInputStickerSetItem sticker;
    
    public TLRequestStickersAddStickerToSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputStickerSet getStickerset() {
        return stickerset;
    }

    public void setStickerset(TLAbsInputStickerSet stickerset) {
        this.stickerset = stickerset;
    }

    public TLInputStickerSetItem getSticker() {
        return sticker;
    }

    public void setSticker(TLInputStickerSetItem sticker) {
        this.sticker = sticker;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.stickerset, stream);
        StreamingUtils.writeTLObject(this.sticker, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.stickerset = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
        this.sticker = StreamingUtils.readTLObject(stream, context, TLInputStickerSetItem.class);
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
        return "stickers.addStickerToSet#8653febe";
    }

}