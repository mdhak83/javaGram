package org.telegram.api.document.base.attribute;

import org.telegram.api.sticker.base.input.set.TLAbsInputStickerSet;
import org.telegram.api.sticker.base.TLMaskCoords;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * documentAttributeSticker
 * Defines a sticker
 * documentAttributeSticker#6319d612 flags:# mask:flags.1?true alt:string stickerset:InputStickerSet mask_coords:flags.0?MaskCoords = DocumentAttribute;
 */
public class TLDocumentAttributeSticker extends TLAbsDocumentAttribute {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6319d612;

    private static final int FLAG_MASK_COORDS   = 0x00000001;   // 0 : Mask coordinates (if this is a mask sticker, attached to a photo)
    private static final int FLAG_MASK          = 0x00000002;   // 1 : Whether this is a mask sticker

    /**
     * Alternative emoji representation of sticker
     */
    private String alt;

    /**
     * Associated stickerset
     */
    private TLAbsInputStickerSet stickerset;

    /**
     * Mask coordinates (if this is a mask sticker, attached to a photo)
     */
    private TLMaskCoords maskCoords;

    public TLDocumentAttributeSticker() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isMask() {
        return this.isFlagSet(FLAG_MASK);
    }

    public void setMask(boolean value) {
        this.setFlag(FLAG_MASK, value);
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public TLAbsInputStickerSet getStickerset() {
        return this.stickerset;
    }

    public void setStickerset(TLAbsInputStickerSet stickerset) {
        this.stickerset = stickerset;
    }

    public boolean hasMaskCoords() {
        return this.isFlagSet(FLAG_MASK_COORDS);
    }

    public TLMaskCoords getMaskCoords() {
        return maskCoords;
    }

    public void setMaskCoords(TLMaskCoords maskCoords) {
        this.maskCoords = maskCoords;
        this.setFlag(FLAG_MASK, maskCoords != null);
    }

   @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.alt, stream);
        StreamingUtils.writeTLObject(this.stickerset, stream);
        if (this.hasMaskCoords()) {
            StreamingUtils.writeTLObject(this.maskCoords, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.alt = StreamingUtils.readTLString(stream);
        this.stickerset = StreamingUtils.readTLObject(stream, context, TLAbsInputStickerSet.class);
        if (this.hasMaskCoords()) {
            this.maskCoords = StreamingUtils.readTLObject(stream, context, TLMaskCoords.class);
        }
    }

    @Override
    public String toString() {
        return "documentAttributeSticker#6319d612";
    }

}