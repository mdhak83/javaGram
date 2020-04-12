package org.javagram.api.file.base.input.encrypted;

/**
 * The type TL input encrypted file empty.
 */
public class TLInputEncryptedFileEmpty extends TLAbsInputEncryptedFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1837c364;

    public TLInputEncryptedFileEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputEncryptedFileEmpty#1837c364";
    }

}