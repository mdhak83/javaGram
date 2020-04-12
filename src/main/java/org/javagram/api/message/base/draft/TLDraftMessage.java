package org.javagram.api.message.base.draft;

import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * draftMessage
 * Represents a message @see <a href="https://core.telegram.org/api/drafts">draft</a>.
 * draftMessage#fd8e711f flags:# no_webpage:flags.1?true reply_to_msg_id:flags.0?int message:string entities:flags.3?Vector&lt;MessageEntity&gt; date:int = DraftMessage;
 */
public class TLDraftMessage extends TLAbsDraftMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfd8e711f;

    private static final int FLAG_REPLY_TO_MSG_ID = 0x00000001; // 0 : The message this message will reply to
    private static final int FLAG_NO_WEBPAGE      = 0x00000002; // 1 : Whether no webpage preview will be generated
    private static final int FLAG_ENTITIES        = 0x00000008; // 3 : Message @see <a href="https://core.telegram.org/api/entities">entities</a> for styled text.

    /**
     * The message this message will reply to
     */
    private int replyToMsgId;
    
    /**
     * The draft
     */
    private String message;
    
    /**
     * Message @see <a href="https://core.telegram.org/api/entities">entities</a> for styled text.
     */
    private TLVector<TLAbsMessageEntity> entities;
    
    /**
     * Date of last update of the draft.
     */
    private int date;

    public TLDraftMessage() {
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

    public boolean hasReplyToMsgId() {
        return this.isFlagSet(FLAG_REPLY_TO_MSG_ID);
    }
    
    public int getReplyToMsgId() {
        return replyToMsgId;
    }

    public void setReplyToMsgId(int replyToMsgId) {
        this.replyToMsgId = replyToMsgId;
        this.setFlag(FLAG_REPLY_TO_MSG_ID, true);
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
        this.setFlag(FLAG_ENTITIES, entities != null && !entities.isEmpty());
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasReplyToMsgId()) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeTLString(this.message, stream);
        if (this.hasEntities()) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasReplyToMsgId()) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        this.message = StreamingUtils.readTLString(stream);
        if (this.hasEntities()) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "draftMessage#fd8e711f";
    }

}