package org.javagram.api.page.base.block;

import org.javagram.api.richtext.base.TLAbsRichText;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Kicker
 * pageBlockKicker#1e148390 text:RichText = PageBlock;
 */
public class TLPageBlockKicker extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1e148390;

    /**
     * Contents
     */
    private TLAbsRichText text;

    public TLPageBlockKicker() {
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
        return "pageBlockKicker#1e148390";
    }
    
}