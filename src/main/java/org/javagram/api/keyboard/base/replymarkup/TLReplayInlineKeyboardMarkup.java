package org.javagram.api.keyboard.base.replymarkup;

import org.javagram.api.keyboard.base.TLKeyboardButtonRow;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Hide custom keyboard
 * @since 07/JUL/2015
 */
public class TLReplayInlineKeyboardMarkup extends TLAbsReplyMarkup {
    public static final int CLASS_ID = 0x48a30254;

    private TLVector<TLKeyboardButtonRow> rows = new TLVector<>();

    public TLReplayInlineKeyboardMarkup() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLKeyboardButtonRow> getRows() {
        return rows;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.rows, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.rows = StreamingUtils.readTLVector(stream, context,TLKeyboardButtonRow.class);
    }

    @Override
    public String toString() {
        return "replyInlineMarkup#48a30254";
    }

}
