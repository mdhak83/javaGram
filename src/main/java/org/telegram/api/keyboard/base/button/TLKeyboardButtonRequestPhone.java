package org.telegram.api.keyboard.base.button;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Button to request a user's phone number
 * keyboardButtonRequestPhone#b16a6c29 text:string = KeyboardButton;
 */
public class TLKeyboardButtonRequestPhone extends TLAbsKeyboardButton {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb16a6c29;

    public TLKeyboardButtonRequestPhone() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.getText(), stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.setText(StreamingUtils.readTLString(stream));
    }

    @Override
    public String toString() {
        return "keyboardButtonRequestPhone#b16a6c29";
    }

}