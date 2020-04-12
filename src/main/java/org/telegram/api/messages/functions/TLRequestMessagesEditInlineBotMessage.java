package org.telegram.api.messages.functions;

import org.telegram.api.bot.base.input.TLInputBotInlineMessageId;
import org.telegram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.telegram.api.message.base.entity.TLAbsMessageEntity;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.input.media.TLAbsInputMedia;

/**
 * Edit an inline bot message
 * messages.editInlineBotMessage#83557dba flags:# no_webpage:flags.1?true id:InputBotInlineMessageID message:flags.11?string media:flags.14?InputMedia reply_markup:flags.2?ReplyMarkup entities:flags.3?Vector&lt;MessageEntity&gt; = Bool;
 */
public class TLRequestMessagesEditInlineBotMessage extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x83557dba;

    private static final int FLAG_NO_WEBPAGE     = 0x00000002; //  1 : Disable webpage preview
    private static final int FLAG_REPLY_MARKUP   = 0x00000004; //  2 : Reply markup for inline keyboards
    private static final int FLAG_ENTITIES       = 0x00000008; //  3 : @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
    private static final int FLAG_MESSAGE        = 0x00000800; // 11 : Message
    private static final int FLAG_MEDIA          = 0x00004000; // 14 : Media

    /**
     * Sent inline message ID
     */
    private TLInputBotInlineMessageId id;

    /**
     * Message
     */
    private String message;

    /**
     * Media
     */
    private TLAbsInputMedia media;

    /**
     * Reply markup for inline keyboards
     */
    private TLAbsReplyMarkup replyMarkup;

    /**
     * @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
     */
    private TLVector<TLAbsMessageEntity> entities;

    public TLRequestMessagesEditInlineBotMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isNoWebpage() {
        return this.isFlagSet(FLAG_NO_WEBPAGE);
    }

    public void setNoWebpage(boolean value) {
        this.setFlag(FLAG_NO_WEBPAGE, value);
    }

    public TLInputBotInlineMessageId getId() {
        return id;
    }

    public void setId(TLInputBotInlineMessageId id) {
        this.id = id;
    }

    public boolean hasMessage() {
        return this.isFlagSet(FLAG_MESSAGE);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.setFlag(FLAG_MESSAGE, this.message != null);
    }

    public boolean hasMedia() {
        return this.isFlagSet(FLAG_MEDIA);
    }

    public TLAbsInputMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsInputMedia media) {
        this.media = media;
        this.setFlag(FLAG_MEDIA, this.media != null);
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.id, stream);
        if (this.hasMessage()) {
            StreamingUtils.writeTLString(this.message, stream);
        }
        if (this.hasMedia()) {
            StreamingUtils.writeTLObject(this.media, stream);
        }
        if (this.hasReplyMarkup()) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
        if (this.hasEntities()) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readTLObject(stream, context, TLInputBotInlineMessageId.class);
        if (this.hasMessage()) {
            this.message = StreamingUtils.readTLString(stream);
        }
        if (this.hasMedia()) {
            this.media = StreamingUtils.readTLObject(stream, context, TLAbsInputMedia.class);
        }
        if (this.hasReplyMarkup()) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
        if (this.hasEntities()) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "messages.editInlineBotMessage#83557dba";
    }

}