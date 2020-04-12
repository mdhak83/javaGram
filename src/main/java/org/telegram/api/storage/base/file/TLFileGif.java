package org.telegram.api.storage.base.file;

/**
 * The type TL file gif.
 */
public class TLFileGif extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcae1aadf;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.fileGif#cae1aadf";
    }

}