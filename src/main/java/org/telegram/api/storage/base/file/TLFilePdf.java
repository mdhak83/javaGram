package org.telegram.api.storage.base.file;

/**
 * The type TL file pdf.
 */
public class TLFilePdf extends TLAbsFileType {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xae1e508d;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "storage.filePdf#ae1e508d";
    }

}