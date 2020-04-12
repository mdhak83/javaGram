package org.javagram.api.bot.base.inlinemessage;

import org.javagram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * botInlineMessageText
 * Send a simple text message
 * botInlineMessageText#8c7f65e2 flags:# no_webpage:flags.0?true message:string entities:flags.1?Vector&lt;MessageEntity&gt; reply_markup:flags.2?ReplyMarkup = BotInlineMessage;
 */
public class TLBotInlineMessageText extends TLAbsBotInlineMessage {

    /**
     * The constant CLASS_ID.
     */
     public static final int CLASS_ID = 0x8c7f65e2;

    private static final int FLAG_NO_WEBPAGE    = 0x00000001; // 0 : Disable webpage preview
    private static final int FLAG_ENTITIES      = 0x00000002; // 1 : @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
    private static final int FLAG_REPLY_MARKUP  = 0x00000004; // 2 : Inline keyboard

    /**
     * The message
     */
    private String message;

    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;

    /**
     * Inline keyboard
     */
    private TLAbsReplyMarkup replyMarkup;

    public TLBotInlineMessageText() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasNoWebpage() {
        return this.isFlagSet(FLAG_NO_WEBPAGE);
    }
    
    public void setNoWebpage(boolean value) {
        this.setFlag(FLAG_NO_WEBPAGE, value);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasEntities() {
        return this.isFlagSet(FLAG_ENTITIES);
    }
    
    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
        this.setFlag(FLAG_ENTITIES, this.entities != null && !this.entities.isEmpty());
    }

    public boolean hasReplyMarkup() {
        return this.isFlagSet(FLAG_REPLY_MARKUP);
    }
    
    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        this.setFlag(FLAG_REPLY_MARKUP, this.replyMarkup != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.message, stream);
        if (this.hasEntities()) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
        if (this.hasReplyMarkup()) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        if (this.hasEntities()) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
        if (this.hasReplyMarkup()) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
    }

    @Override
    public String toString() {
        return "botInlineMessageText#8c7f65e2";
    }

}