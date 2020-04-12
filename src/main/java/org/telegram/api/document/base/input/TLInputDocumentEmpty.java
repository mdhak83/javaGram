package org.telegram.api.document.base.input;

/**
 * The type TL input document empty.
 */
public class TLInputDocumentEmpty extends TLAbsInputDocument {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x72f0eaae;

    public TLInputDocumentEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "inputDocumentEmpty#72f0eaae";
    }

}