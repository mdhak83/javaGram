package org.telegram.api.stickers.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.document.base.input.TLAbsInputDocument;
import org.telegram.api.messages.base.stickers.TLMessagesStickerSet;

/**
 * Changes the absolute position of a sticker in the set to which it belongs; for bots only. The sticker set must have been created by the bot
 * stickers.changeStickerPosition#ffb6d4ca sticker:InputDocument position:int = messages.StickerSet;
 */
public class TLRequestStickersChangeStickerPosition extends TLMethod<TLMessagesStickerSet> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xffb6d4ca;

    /**
     * The sticker
     */
    private TLAbsInputDocument sticker;
    
    /**
     * The new position of the sticker, zero-based
     */
    private int position;
    
    public TLRequestStickersChangeStickerPosition() {
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.sticker, stream);
        StreamingUtils.writeInt(this.position, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.sticker = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
        this.position = StreamingUtils.readInt(stream);
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
        return "stickers.changeStickerPosition#ffb6d4ca";
    }

}