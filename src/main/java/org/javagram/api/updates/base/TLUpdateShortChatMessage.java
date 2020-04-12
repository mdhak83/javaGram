package org.javagram.api.updates.base;

import org.javagram.api.message.base.TLMessageFwdHeader;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.client.handlers.AbstractUpdatesHandler;

/**
 * The type TL update short chat message.
 */
public class TLUpdateShortChatMessage extends TLAbsUpdates {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x16812688;

    private static final int FLAG_UNUSED_0           = 0x00000001; // 0
    private static final int FLAG_OUT                = 0x00000002; // 1
    private static final int FLAG_FWD                = 0x00000004; // 2
    private static final int FLAG_REPLY              = 0x00000008; // 3
    private static final int FLAG_MENTION            = 0x00000010; // 4
    private static final int FLAG_CONTENT_UNREAD     = 0x00000020; // 5
    private static final int FLAG_UNUSED6            = 0x00000040; // 6
    private static final int FLAG_ENTITIES           = 0x00000080; // 7
    private static final int FLAG_UNUSED8            = 0x00000100; // 8
    private static final int FLAG_UNUSED9            = 0x00000200; // 9
    private static final int FLAG_UNUSED10           = 0x00000400; // 10
    private static final int FLAG_VIA_BOT_ID         = 0x00000800; // 11
    private static final int FLAG_UNUSED_12          = 0x00001000; // 12
    private static final int FLAG_SILENT             = 0x00002000; // 13

    private int id;
    private int fromId;
    private int chatId;
    private String message = "";
    private int pts;
    private int ptsCount;
    private int date;
    private TLMessageFwdHeader fwdFrom;
    private int viaBotId;
    private int replyToMsgId;
    private TLVector<TLAbsMessageEntity> entities;

    public TLUpdateShortChatMessage() {
        super();
    }

    /**
     * Gets from id.
     *
     * @return the from id
     */
    public int getFromId() {
        return this.fromId;
    }

    /**
     * Sets from id.
     *
     * @param fromId the from id
     */
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    /**
     * Gets chat id.
     *
     * @return the chat id
     */
    public int getChatId() {
        return this.chatId;
    }

    /**
     * Sets chat id.
     *
     * @param chatId the chat id
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets pts.
     *
     * @return the pts
     */
    public int getPts() {
        return this.pts;
    }

    /**
     * Sets pts.
     *
     * @param pts the pts
     */
    public void setPts(int pts) {
        this.pts = pts;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets pts count.
     *
     * @return the pts count
     */
    public int getPtsCount() {
        return this.ptsCount;
    }

    /**
     * Sets pts count.
     *
     * @param ptsCount the pts count
     */
    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    public TLMessageFwdHeader getFwdFrom() {
        return fwdFrom;
    }

    public void setFwdFrom(TLMessageFwdHeader fwdFrom) {
        this.fwdFrom = fwdFrom;
    }

    /**
     * Gets reply to msg id.
     *
     * @return the reply to msg id
     */
    public int getReplyToMsgId() {
        return this.replyToMsgId;
    }

    /**
     * Sets reply to msg id.
     *
     * @param replyToMsgId the reply to msg id
     */
    public void setReplyToMsgId(int replyToMsgId) {
        this.replyToMsgId = replyToMsgId;
    }

    public TLVector<TLAbsMessageEntity> getEntities() {
        return hasEntities() ? entities : new TLVector<>();
    }

    public void setEntities(TLVector<TLAbsMessageEntity> entities) {
        this.entities = entities;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isMention() {
        return this.isFlagSet(FLAG_MENTION);
    }

    /**
     * Is sent.
     *
     * @return the boolean
     */
    public boolean isSent() {
        return this.isFlagSet(FLAG_OUT);
    }

    public boolean isUnreadContent() {
        return this.isFlagSet(FLAG_CONTENT_UNREAD);
    }

    public boolean isForwarded() {
        return this.isFlagSet(FLAG_FWD);
    }

    public boolean hasEntities() {
        return this.isFlagSet(FLAG_ENTITIES);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeInt(this.fromId, stream);
        StreamingUtils.writeInt(this.chatId, stream);
        StreamingUtils.writeTLString(this.message, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
        StreamingUtils.writeInt(this.date, stream);
        if ((this.flags & FLAG_FWD) != 0) {
            StreamingUtils.writeTLObject(this.fwdFrom, stream);
        }
        if ((this.flags & FLAG_VIA_BOT_ID) != 0) {
            StreamingUtils.writeInt(this.viaBotId, stream);
        }
        if ((this.flags & FLAG_REPLY) != 0) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.fromId = StreamingUtils.readInt(stream);
        this.chatId = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FWD) != 0) {
            this.fwdFrom = StreamingUtils.readTLObject(stream, context, TLMessageFwdHeader.class);
        }
        if ((this.flags & FLAG_VIA_BOT_ID) != 0) {
            this.viaBotId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_REPLY) != 0) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_ENTITIES) != 0) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
    }

    @Override
    public String toString() {
        return "updateShortChatMessage#16812688";
    }
    
    public String toLog() {
        return "Message#" + this.id + " - Chat: " + this.chatId + " - User: " + this.fromId + " - Content: '" + (this.message != null ? this.message : "---") + "'";
    }

}