package org.javagram.api.richtext.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Rich text linked to a phone number
 * textPhone#1ccb966a text:RichText phone:string = RichText;
 */
public class TLTextPhone extends TLAbsRichText {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1ccb966a;

    /**
     * Text
     */
    private TLAbsRichText text;
    
    /**
     * Phone number
     */
    private String phone;

    public TLTextPhone() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.text, stream);
        StreamingUtils.writeTLString(this.phone, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLObject(stream, context, TLAbsRichText.class);
        this.phone = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "textPhone#1ccb966a";
    }

}