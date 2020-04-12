package org.javagram.api.page.base.list;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.richtext.base.TLAbsRichText;

/**
 * Item in block list
 */
public class TLPageListItem extends TLAbsPageListItem {

    /**
     * The constant CLASS_ID.
     */
   public static final int CLASS_ID = 0xb92fb6cd;

    /**
     * Text
     */
    private TLAbsRichText text;

    public TLPageListItem() {
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
        return "pageListItemText#b92fb6cd";
    }

}