package org.telegram.api.richtext.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Text linking to another section of the page
 * textAnchor#35553762 text:RichText name:string = RichText;
 */
public class TLTextAnchor extends TLAbsRichText {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x35553762;

    /**
     * Text
     */
    private TLAbsRichText text;
    
    /**
     * Section name
     */
    private String name;

    public TLTextAnchor() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.text, stream);
        StreamingUtils.writeTLString(this.name, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        this.name = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "textAnchor#35553762";
    }

}