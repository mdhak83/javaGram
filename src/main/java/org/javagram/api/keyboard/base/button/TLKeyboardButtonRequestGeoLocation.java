package org.javagram.api.keyboard.base.button;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Button to request a user's geolocation
 * keyboardButtonRequestGeoLocation#fc796b3f text:string = KeyboardButton;
 */
public class TLKeyboardButtonRequestGeoLocation extends TLAbsKeyboardButton {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfc796b3f;

    public TLKeyboardButtonRequestGeoLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "keyboardButtonRequestGeoLocation#fc796b3f";
    }

}