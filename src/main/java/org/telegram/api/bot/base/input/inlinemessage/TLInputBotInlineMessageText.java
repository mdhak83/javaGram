package org.telegram.api.bot.base.input.inlinemessage;

import org.telegram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.telegram.api.message.base.entity.TLAbsMessageEntity;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
  * @since 13/FEB/2016
 */
public class TLInputBotInlineMessageText extends TLAbsInputBotInlineMessage {
    public static final int CLASS_ID = 0x3dcd7a87;

    private static final int FLAG_NO_WEBPAGE    = 0x00000001; // 0
    private static final int FLAG_ENTITIES      = 0x00000002; // 1
    private static final int FLAG_REPLY_MARKUP  = 0x00000004; // 2

    private String message;
    private TLVector<TLAbsMessageEntity> entities;
    private TLAbsReplyMarkup replyMarkup;

    public TLInputBotInlineMessageText() {
        super();
    }

    public int getFlags() {
        return flags;
    }

    public String getMessage() {
        return message;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public boolean hasWebPreview() {
        return (flags & FLAG_NO_WEBPAGE) == 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.message, stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
        if ((flags & FLAG_REPLY_MARKUP) != 0) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
        if ((flags & FLAG_REPLY_MARKUP) != 0) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
    }

    @Override
    public String toString() {
        return "inputBotInlineMessageText#3dcd7a87";
    }

}
