package org.javagram.api.input.media;

/**
 * The type TL input media empty.
 */
public class TLInputMediaEmpty extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9664f57f;

    public TLInputMediaEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputMediaEmpty#9664f57f";
    }

}