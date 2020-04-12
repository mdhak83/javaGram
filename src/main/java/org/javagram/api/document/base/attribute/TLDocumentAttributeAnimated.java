package org.javagram.api.document.base.attribute;

/**
 * documentAttributeAnimated
 * Defines an animated GIF
 * documentAttributeAnimated#11b58939 = DocumentAttribute;
 */
public class TLDocumentAttributeAnimated extends TLAbsDocumentAttribute {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x11b58939;

    public TLDocumentAttributeAnimated() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "documentAttributeAnimated#11b58939";
    }

}