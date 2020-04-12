package org.javagram.api.document.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represent an empty document
 * @author Ruben Bermudez
 * @version 2.0
 * @since 11/APR/2015
 */
public class TLDocumentEmpty extends TLAbsDocument {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x36f8c871;

    public TLDocumentEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "documentEmpty#36f8c871";
    }

}