package org.telegram.api.richtext.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Highlighted text
 * textMarked#34b8621 text:RichText = RichText;
 */
public class TLTextMarked extends TLAbsRichText {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x34b8621;

    /**
     * Text
     */
    private TLAbsRichText text;

    public TLTextMarked() {
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
        return "textMarked#34b8621";
    }

}