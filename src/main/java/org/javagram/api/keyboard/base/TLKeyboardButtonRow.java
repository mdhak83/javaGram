package org.javagram.api.keyboard.base;

import org.javagram.api.keyboard.base.button.TLAbsKeyboardButton;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Inline keyboard row
 * keyboardButtonRow#77608b83 buttons:Vector&lt;KeyboardButton&gt; = KeyboardButtonRow;
 */
public class TLKeyboardButtonRow extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x77608b83;

    /**
     * Bot or inline keyboard buttons
     */
    public TLVector<TLAbsKeyboardButton> buttons = new TLVector<>();

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.buttons, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.buttons = StreamingUtils.readTLVector(stream, context, TLAbsKeyboardButton.class);
    }

    @Override
    public String toString() {
        return "keyboard.KeyboardButtonRow#77608b83";
    }

}