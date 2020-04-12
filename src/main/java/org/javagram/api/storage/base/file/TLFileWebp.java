package org.javagram.api.storage.base.file;

/**
 * The type TL file webp.
 */
public class TLFileWebp extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1081464c;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.fileWebp#1081464c";
    }

}