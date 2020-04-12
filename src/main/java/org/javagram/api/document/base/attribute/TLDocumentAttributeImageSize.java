package org.javagram.api.document.base.attribute;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * documentAttributeImageSize
 * Defines the width and height of an image uploaded as document
 * documentAttributeImageSize#6c37c15c w:int h:int = DocumentAttribute;
 */
public class TLDocumentAttributeImageSize extends TLAbsDocumentAttribute {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6c37c15c;

    /**
     * Width of image
     */
    private int w;

    /**
     * Height of image
     */
    private int h;

    public TLDocumentAttributeImageSize() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "documentAttributeImageSize#6c37c15c";
    }

}