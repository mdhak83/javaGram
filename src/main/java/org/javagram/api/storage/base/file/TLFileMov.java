package org.javagram.api.storage.base.file;

/**
 * The type TL file mov.
 */
public class TLFileMov extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4b09ebbc;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.fileMov#4b09ebbc";
    }

}