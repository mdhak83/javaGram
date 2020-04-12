package org.javagram.api.bot.base.input.inlinemessage;

import org.javagram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 */
public class TLInputBotInlineMessageGame extends TLAbsInputBotInlineMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4b425864;

    private static final int FLAG_REPLY_MARKUP  = 0x00000004; // 2

    private TLAbsReplyMarkup replyMarkup;

    public TLInputBotInlineMessageGame() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_REPLY_MARKUP) != 0) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_REPLY_MARKUP) != 0) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
    }

    @Override
    public String toString() {
        return "inputBotInlineMessageGame#4b425864";
    }

}
