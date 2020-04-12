package org.javagram.api.messages.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.input.media.TLAbsInputMedia;

/**
 * Edit message
 * messages.editMessage#48f71778 flags:# no_webpage:flags.1?true peer:InputPeer id:int message:flags.11?string media:flags.14?InputMedia reply_markup:flags.2?ReplyMarkup entities:flags.3?Vector&lt;MessageEntity&gt; schedule_date:flags.15?int = Updates;
 */
public class TLRequestMessagesEditMessage extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x48f71778;

    private static final int FLAG_NO_WEBPAGE    = 0x00000002; //  1 : Disable webpage preview
    private static final int FLAG_REPLY_MARKUP  = 0x00000004; //  2 : Reply markup for inline keyboards
    private static final int FLAG_ENTITIES      = 0x00000008; //  3 : @see <a href="https://core.telegram.org/api/entities">Message entities for styled text</a>
    private static final int FLAG_MESSAGE       = 0x00000800; // 11 : New message
    private static final int FLAG_MEDIA         = 0x00004000; // 14 : New attached media
    private static final int FLAG_SCHEDULE_DATE = 0x00008000; // 15 : Scheduled message date for scheduled messages

    /**
     * Where was the message sent
     */
    private TLAbsInputPeer peer;

    /**
     * ID of the message to edit
     */
    private int id;

    /**
     * New message
     */
    private String message;

    /**
     * New attached media
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

    /**
     * Scheduled message date for scheduled messages
     */
    private int scheduleDate;

    public TLRequestMessagesEditMessage() {
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

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasMessage() {
        return (this.isFlagSet(FLAG_MESSAGE));
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.setFlag(FLAG_MESSAGE, this.message != null);
    }

    public boolean hasMedia() {
        return (this.isFlagSet(FLAG_MEDIA));
    }
    
    public TLAbsInputMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsInputMedia media) {
        this.media = media;
        this.setFlag(FLAG_REPLY_MARKUP, this.media != null);
    }

    public boolean hasReplyMarkup() {
        return (this.isFlagSet(FLAG_REPLY_MARKUP));
    }
    
    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        this.setFlag(FLAG_REPLY_MARKUP, this.replyMarkup != null);
    }

    public boolean hasEntities() {
        return (this.isFlagSet(FLAG_ENTITIES));
    }
    
    public TLVector<TLAbsMessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
        this.setFlag(FLAG_ENTITIES, this.entities != null && !this.entities.isEmpty());
    }

    public boolean hasScheduleDate() {
        return (this.isFlagSet(FLAG_SCHEDULE_DATE));
    }
    
    public int getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(int scheduleDate) {
        this.scheduleDate = scheduleDate;
        this.setFlag(FLAG_SCHEDULE_DATE, this.scheduleDate != 0);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.id, stream);
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
        if (this.hasScheduleDate()) {
            StreamingUtils.writeInt(this.scheduleDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.id = StreamingUtils.readInt(stream);
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
        if (this.hasScheduleDate()) {
            this.scheduleDate = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "messages.editMessage#48f71778";
    }

}