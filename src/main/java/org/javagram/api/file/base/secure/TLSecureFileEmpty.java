package org.javagram.api.file.base.secure;

/**
 * Empty constructor
 * secureFileEmpty#64199744 = SecureFile;
 */
public class TLSecureFileEmpty extends TLAbsSecureFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x64199744;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "secureFileEmpty#64199744";
    }
    
}