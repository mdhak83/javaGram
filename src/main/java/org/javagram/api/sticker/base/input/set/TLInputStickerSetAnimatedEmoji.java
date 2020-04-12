package org.javagram.api.sticker.base.input.set;

/**
 * Animated emojis stickerset
 * inputStickerSetAnimatedEmoji#28703c8 = InputStickerSet;
 */
public class TLInputStickerSetAnimatedEmoji extends TLAbsInputStickerSet {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x28703c8;

    public TLInputStickerSetAnimatedEmoji() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputStickerSetAnimatedEmoji#28703c8";
    }

}