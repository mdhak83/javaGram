package org.telegram.api.document.base.attribute;

/**
 * documentAttributeHasStickers
 * Whether the current document has stickers attached
 * documentAttributeHasStickers#9801d2f7 = DocumentAttribute;
 */
public class TLDocumentAttributeHasStickers extends TLAbsDocumentAttribute {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9801d2f7;

    public TLDocumentAttributeHasStickers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "documentAttributeHasStickers#9801d2f7";
    }

}