package org.javagram.api.richtext.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Subscript text
 * textSubscript#ed6a8504 text:RichText = RichText;
 */
public class TLTextSubscript extends TLAbsRichText {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xed6a8504;

    /**
     * Text
     */
    private TLAbsRichText text;

    public TLTextSubscript() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsRichText getText() {
        return text;
    }

    public void setText(TLAbsRichText text) {
        this.text = text;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "textSubscript#ed6a8504";
    }

}