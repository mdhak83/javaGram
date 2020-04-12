package org.telegram.api.storage.base.file;

/**
 * The type TL file mp 3.
 */
public class TLFileMp3 extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x528a0677;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.fileMp3#528a0677";
    }

}