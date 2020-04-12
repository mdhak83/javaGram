package org.telegram.api.page.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.richtext.base.TLAbsRichText;
import org.telegram.api._primitives.TLObject;

/**
 * Page caption
 */
public class TLPageCaption extends TLObject {

    public static final int CLASS_ID = 0x6f747657;

    /**
     * Caption
     */
    private TLAbsRichText text;

    /**
     * Credits
     */
    private TLAbsRichText credit;

    public TLPageCaption() {
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

    public TLAbsRichText getCredit() {
        return credit;
    }

    public void setCredit(TLAbsRichText credit) {
        this.credit = credit;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.text, stream);
        StreamingUtils.writeTLObject(this.credit, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        this.credit = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
    }

    @Override
    public String toString() {
        return "pageCaption#6f747657";
    }

}