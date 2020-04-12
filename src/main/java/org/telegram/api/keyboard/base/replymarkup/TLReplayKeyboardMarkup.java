package org.telegram.api.keyboard.base.replymarkup;

import org.telegram.api.keyboard.base.TLKeyboardButtonRow;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Hide custom keyboard
 * @since 07/JUL/2015
 */
public class TLReplayKeyboardMarkup extends TLAbsReplyMarkup {
    public static final int CLASS_ID = 0x3502758c;

    private static final int FLAG_RESIZE    = 0x00000001; // 0
    private static final int FLAG_SINGLEUSE = 0x00000002; // 1
    private static final int FLAG_SELECTIVE = 0x00000004; // 2

    private TLVector<TLKeyboardButtonRow> rows = new TLVector<>();
    protected int flags;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLKeyboardButtonRow> getRows() {
        return rows;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLVector(this.rows, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.rows = StreamingUtils.readTLVector(stream, context, TLKeyboardButtonRow.class);
    }

    @Override
    public String toString() {
        return "keyboard.ReplyKeyboardMarkup#3502758c";
    }

}
