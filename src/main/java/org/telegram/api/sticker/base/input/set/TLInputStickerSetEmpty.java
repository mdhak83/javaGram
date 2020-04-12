package org.telegram.api.sticker.base.input.set;

/**
 * Empty constructor
 * inputStickerSetEmpty#ffb62b95 = InputStickerSet;
 */
public class TLInputStickerSetEmpty extends TLAbsInputStickerSet {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xffb62b95;

    public TLInputStickerSetEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputStickerSetEmpty#ffb62b95";
    }

}