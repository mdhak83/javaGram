package org.telegram.api.photo.base.input;

/**
 * The type TL input photo empty.
 */
public class TLInputPhotoEmpty extends TLAbsInputPhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1cd7bf0d;

    public TLInputPhotoEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputPhotoEmpty#1cd7bf0d";
    }

}