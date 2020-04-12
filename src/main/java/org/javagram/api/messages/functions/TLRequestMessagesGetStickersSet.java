package org.javagram.api.messages.functions;

import org.javagram.api.sticker.base.input.set.TLAbsInputStickerSet;
import org.javagram.api.messages.base.stickers.TLMessagesStickerSet;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get info about a stickerset
 * messages.getStickerSet#2619a90e stickerset:InputStickerSet = messages.StickerSet;
 */
public class TLRequestMessagesGetStickersSet extends TLMethod<TLMessagesStickerSet> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2619a90e;

    /**
     * Stickerset
     */
    private TLAbsInputStickerSet stickerSet;

    public TLRequestMessagesGetStickersSet() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputStickerSet getStickerSet() {
        return stickerSet;
    }

    public void setStickerSet(TLAbsInputStickerSet stickerSet) {
        this.stickerSet = stickerSet;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.stickerSet, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.stickerSet = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
    }

    @Override
    public TLMessagesStickerSet deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesStickerSet) {
            return (TLMessagesStickerSet) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesStickerSet.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "stickers.getStickersSet#2619a90e";
    }

}