package org.telegram.api.storage.base.file;

/**
 * The type TL file jpeg.
 */
public class TLFileJpeg extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7efe0e;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.fileJpeg#7efe0e";
    }

}