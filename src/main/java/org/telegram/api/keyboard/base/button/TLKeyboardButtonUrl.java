package org.telegram.api.keyboard.base.button;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * URL button
 * keyboardButtonUrl#258aff05 text:string url:string = KeyboardButton;
 */
public class TLKeyboardButtonUrl extends TLAbsKeyboardButton {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x258aff05;

    /**
     * URL
     */
    private String url;

    public TLKeyboardButtonUrl() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLString(this.url, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
        this.url = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "keyboardButtonUrl#258aff05";
    }

}