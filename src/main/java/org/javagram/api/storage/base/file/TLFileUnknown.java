package org.javagram.api.storage.base.file;

/**
 * The type TL file unknown.
 */
public class TLFileUnknown extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaa963b05;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.fileUnknown#aa963b05";
    }

}