package org.javagram.api.sticker.base.input.set;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.document.base.input.TLAbsInputDocument;
import org.javagram.api.sticker.base.TLMaskCoords;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;

/**
 * Sticker in a stickerset
 * inputStickerSetItem#ffa0a496 flags:# document:InputDocument emoji:string mask_coords:flags.0?MaskCoords = InputStickerSetItem;
 */
public class TLInputStickerSetItem extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xffa0a496;

    private static final int FLAG_MASK_COORDS   = 0x00000001; // 0

    /**
     * The sticker
     */
    protected TLAbsInputDocument document;
    
    /**
     * Associated emoji
     */
    protected String emoji;
    
    /**
     * Coordinates for mask sticker
     */
    protected TLMaskCoords maskCoords;

    public TLInputStickerSetItem() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputDocument getDocument() {
        return document;
    }

    public void setDocument(TLAbsInputDocument document) {
        this.document = document;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public TLMaskCoords getMaskCoords() {
        return maskCoords;
    }

    public void setMaskCoords(TLMaskCoords maskCoords) {
        this.maskCoords = maskCoords;
        if (this.maskCoords != null) {
            this.flags |= FLAG_MASK_COORDS;
        } else {
            this.flags &= ~FLAG_MASK_COORDS;
        }
    }
    
    public boolean hasMaskCoords() {
        return this.isFlagSet(FLAG_MASK_COORDS);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.document, stream);
        StreamingUtils.writeTLString(this.emoji, stream);
        if (this.hasMaskCoords()) {
            StreamingUtils.writeTLObject(this.maskCoords, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.document = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
        this.emoji = StreamingUtils.readTLString(stream);
        if (this.hasMaskCoords()) {
            this.maskCoords = StreamingUtils.readTLObject(stream, context, TLMaskCoords.class);
        }
    }

    @Override
    public String toString() {
        return "inputStickerSetItem#ffa0a496";
    }

}