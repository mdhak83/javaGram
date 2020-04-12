package org.javagram.api.document.base.attribute;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * documentAttributeFilename
 * A simple document with a file name
 * documentAttributeFilename#15590068 file_name:string = DocumentAttribute;
 */
public class TLDocumentAttributeFilename extends TLAbsDocumentAttribute {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x15590068;

    /**
     * The file name
     */
    private String fileName;

    public TLDocumentAttributeFilename() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.fileName, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.fileName = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "documentAttributeFilename#15590068";
    }

}