package org.javagram.api.richtext.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLTextItalic extends TLAbsRichText {
    public static final int CLASS_ID = 0xd912a59c;

    private TLAbsRichText text;

    public TLTextItalic() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsRichText getText() {
        return text;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "textItalic#d912a59c";
    }

}