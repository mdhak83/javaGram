package org.javagram.api.chat.base.input.photo;

/**
 * The type TL input chat photo empty.
 */
public class TLInputChatPhotoEmpty extends TLAbsInputChatPhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1ca48f57;

    public TLInputChatPhotoEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputChatPhotoEmpty#1ca48f57";
    }

}