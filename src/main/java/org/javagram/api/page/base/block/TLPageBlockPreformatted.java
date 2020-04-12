package org.javagram.api.page.base.block;

import org.javagram.api.richtext.base.TLAbsRichText;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPageBlockPreformatted extends TLAbsPageBlock {
    public static final int CLASS_ID = 0xc070d93e;

    private TLAbsRichText text;
    private String language;

    public TLPageBlockPreformatted() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsRichText getText() {
        return text;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(text, stream);
        StreamingUtils.writeTLString(language, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        language = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "pageBlockPreformatted#c070d93e";
    }

}
