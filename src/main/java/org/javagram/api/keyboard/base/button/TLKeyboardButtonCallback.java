package org.javagram.api.keyboard.base.button;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Callback button
 * keyboardButtonCallback#683a5e46 text:string data:bytes = KeyboardButton;
 */
public class TLKeyboardButtonCallback extends TLAbsKeyboardButton {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x683a5e46;

    /**
     * Callback data
     */
    private TLBytes data;

    public TLKeyboardButtonCallback() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getData() {
        return data;
    }

    public void setData(TLBytes data) {
        this.data = data;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.text, stream);
        StreamingUtils.writeTLBytes(this.data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.text = StreamingUtils.readTLString(stream);
        this.data = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "keyboardButtonCallback#683a5e46";
    }

}