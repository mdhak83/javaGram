package org.javagram.api.richtext.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Superscript text
 * textSuperscript#c7fb5e01 text:RichText = RichText;
 */
public class TLTextSuperscript extends TLAbsRichText {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc7fb5e01;

    /**
     * Text
     */
    private TLAbsRichText text;

    public TLTextSuperscript() {
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
        return "textSuperscript#c7fb5e01";
    }

}