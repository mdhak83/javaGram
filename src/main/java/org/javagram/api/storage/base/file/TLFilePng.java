package org.javagram.api.storage.base.file;

/**
 * The type TL file png.
 */
public class TLFilePng extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa4f63c0;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.filePng#a4f63c0";
    }

}