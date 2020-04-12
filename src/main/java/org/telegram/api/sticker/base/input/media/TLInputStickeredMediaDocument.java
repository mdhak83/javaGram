package org.telegram.api.sticker.base.input.media;

import org.telegram.api.document.base.input.TLAbsInputDocument;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 26/SEP/2016
 */
public class TLInputStickeredMediaDocument extends TLAbsInputStickeredMedia {
    public static final int CLASS_ID = 0x438865b;

    private TLAbsInputDocument id;

    public TLAbsInputDocument getId() {
        return id;
    }

    public void setId(TLAbsInputDocument id) {
        this.id = id;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(id, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLObject(stream, context, TLAbsInputDocument.class);
    }

    @Override
    public String toString() {
        return "inputStickeredMediaDocument#438865b";
    }

}
