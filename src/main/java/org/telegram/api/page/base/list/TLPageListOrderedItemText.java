package org.telegram.api.page.base.list;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.richtext.base.TLAbsRichText;

/**
 * Ordered list of text items
 * pageListOrderedItemText#5e068047 num:string text:RichText = PageListOrderedItem;
 */
public class TLPageListOrderedItemText extends TLAbsPageListOrderedItem {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5e068047;

    /**
     * Number of element within ordered list
     */
    private String num;
    
    /**
     * Text
     */
    private TLAbsRichText text;

    public TLPageListOrderedItemText() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public TLAbsRichText getText() {
        return text;
    }

    public void setText(TLAbsRichText text) {
        this.text = text;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.num, stream);
        StreamingUtils.writeTLObject(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.num = StreamingUtils.readTLString(stream);
        this.text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageListOrderedItemText#5e068047";
    }

}