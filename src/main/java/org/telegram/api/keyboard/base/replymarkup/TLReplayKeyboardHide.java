package org.telegram.api.keyboard.base.replymarkup;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Hide custom keyboard
 * @since 07/JUL/2015
 */
public class TLReplayKeyboardHide extends TLAbsReplyMarkup {
    public static final int CLASS_ID = 0xa03e5b85;

    private static final int FLAG_UNUSED0 = 0x00000001; // 0
    private static final int FLAG_UNUSED1 = 0x00000002; // 1
    private static final int FLAG_SELECTIVE = 0x00000004; // 2

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "keyboard#ReplyKeyboardHide#a03e5b85";
    }

}
