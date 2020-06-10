package org.javagram.api.message.base;

import org.javagram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.api.message.base.media.TLAbsMessageMedia;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.lang3.StringUtils;

/**
 * A message
 * message#452c0e65 flags:# out:flags.1?true mentioned:flags.4?true media_unread:flags.5?true silent:flags.13?true post:flags.14?true from_scheduled:flags.18?true legacy:flags.19?true edit_hide:flags.21?true id:int from_id:flags.8?int to_id:Peer fwd_from:flags.2?MessageFwdHeader via_bot_id:flags.11?int reply_to_msg_id:flags.3?int date:int message:string media:flags.9?MessageMedia reply_markup:flags.6?ReplyMarkup entities:flags.7?Vector&lt;MessageEntity&gt; views:flags.10?int edit_date:flags.15?int post_author:flags.16?string grouped_id:flags.17?long restriction_reason:flags.22?Vector&lt;RestrictionReason&gt; = Message;
 */
public class TLMessage extends TLAbsMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x452c0e65;

    private static final int FLAG_OUT                   = 0x00000002; // 1
    private static final int FLAG_FWD_FROM              = 0x00000004; // 2
    private static final int FLAG_REPLY_TO_MSG_ID       = 0x00000008; // 3
    private static final int FLAG_MENTIONED             = 0x00000010; // 4
    private static final int FLAG_MEDIA_UNREAD          = 0x00000020; // 5
    private static final int FLAG_REPLY_MARKUP          = 0x00000040; // 6
    private static final int FLAG_ENTITIES              = 0x00000080; // 7
    private static final int FLAG_FROM_ID               = 0x00000100; // 8
    private static final int FLAG_MEDIA                 = 0x00000200; // 9
    private static final int FLAG_VIEWS                 = 0x00000400; // 10
    private static final int FLAG_VIA_BOT_ID            = 0x00000800; // 11
    private static final int FLAG_SILENT                = 0x00002000; // 13
    private static final int FLAG_POST                  = 0x00004000; // 14
    private static final int FLAG_EDIT_DATE             = 0x00008000; // 15
    private static final int FLAG_POST_AUTHOR           = 0x00010000; // 16
    private static final int FLAG_GROUPED_ID            = 0x00020000; // 17
    private static final int FLAG_FROM_SCHEDULED        = 0x00040000; // 18
    private static final int FLAG_LEGACY                = 0x00080000; // 19
    private static final int FLAG_EDIT_HIDE             = 0x00200000; // 21
    private static final int FLAG_RESTRICTION_REASONS   = 0x00400000; // 22

    /**
     * ID of the message
     */
    private int id;
    
    /**
     * ID of the sender of the message
     */
    private int fromId;
    
    /**
     * ID of the chat were the message was sent
     */
    private TLAbsPeer toId;
    
    /**
     * Info about forwarded messages
     */
    private TLMessageFwdHeader fwdFrom;
    
    /**
     * ID of the inline bot that generated the message
     */
    private int viaBotId;
    
    /**
     * ID of the message this message replies to
     */
    private int replyToMsgId;
    
    /**
     * Date of the message
     */
    private int date;
    
    /**
     * The message
     */
    private String message;
    
    /**
     * Media attachment
     */
    private TLAbsMessageMedia media;
    
    /**
     * Reply markup (bot/inline keyboards)
     */
    private TLAbsReplyMarkup replyMarkup;
    
    /**
     * Message @see <a href="https://core.telegram.org/api/entities">entities</a> for styled text
     */
    private TLVector<TLAbsMessageEntity> entities;
    
    /**
     * View count for channel posts
     */
    private int views;
    
    /**
     * Last edit date of this message
     */
    private int editDate;
    
    /**
     * Name of the author of this message for channel posts (with signatures enabled)
     */
    private String postAuthor;
    
    /**
     * Multiple media messages sent using @see <a href="https://core.telegram.org/method/messages.sendMultiMedia">messages.sendMultiMedia</a> with the same grouped ID indicate an album
     */
    private long groupedId;
    
    /**
     * Contains the reason why access to this message must be restricted.
     */
    private TLVector<String> restrictionReasons;
    
    public TLMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isOut() {
        return this.isFlagSet(FLAG_OUT);
    }
    
    public void setOut(boolean value) {
        this.setFlag(FLAG_OUT, value);
    }

    public boolean isMentioned() {
        return this.isFlagSet(FLAG_MENTIONED);
    }

    public void setMentioned(boolean value) {
        this.setFlag(FLAG_MENTIONED, value);
    }

    public boolean isMediaUnread() {
        return this.isFlagSet(FLAG_MEDIA_UNREAD);
    }

    public void setMediaUnread(boolean value) {
        this.setFlag(FLAG_MEDIA_UNREAD, value);
    }

    public boolean isSilent() {
        return this.isFlagSet(FLAG_SILENT);
    }

    public void setSilent(boolean value) {
        this.setFlag(FLAG_SILENT, value);
    }

    public boolean isPost() {
        return this.isFlagSet(FLAG_POST);
    }

    public void setPost(boolean value) {
        this.setFlag(FLAG_POST, value);
    }

    public boolean isFromScheduled() {
        return this.isFlagSet(FLAG_FROM_SCHEDULED);
    }

    public void setFromScheduled(boolean value) {
        this.setFlag(FLAG_FROM_SCHEDULED, value);
    }

    public boolean isLegacy() {
        return this.isFlagSet(FLAG_LEGACY);
    }

    public void setLegacy(boolean value) {
        this.setFlag(FLAG_LEGACY, value);
    }

    public boolean isEditHide() {
        return this.isFlagSet(FLAG_EDIT_HIDE);
    }

    public void setEditHide(boolean value) {
        this.setFlag(FLAG_EDIT_HIDE, value);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasFromId() {
        return this.isFlagSet(FLAG_FROM_ID);
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
        this.setFlag(FLAG_FROM_ID, fromId != 0);
    }

    @Override
    public int getChatId() {
        return getToId().getId();
    }

    public TLAbsPeer getToId() {
        return toId;
    }

    public void setToId(TLAbsPeer toId) {
        this.toId = toId;
    }

    public boolean hasFwdFrom() {
        return this.isFlagSet(FLAG_FWD_FROM);
    }

    public TLMessageFwdHeader getFwdFrom() {
        return fwdFrom;
    }

    public void setFwdFrom(TLMessageFwdHeader fwdFrom) {
        this.fwdFrom = fwdFrom;
        this.setFlag(FLAG_FWD_FROM, fwdFrom != null);
    }

    public boolean hasViaBotId() {
        return this.isFlagSet(FLAG_VIA_BOT_ID);
    }

    public int getViaBotId() {
        return viaBotId;
    }

    public void setViaBotId(int viaBotId) {
        this.viaBotId = viaBotId;
        this.setFlag(FLAG_VIA_BOT_ID, viaBotId != 0);
    }

    public boolean hasReplyToMsgId() {
        return this.isFlagSet(FLAG_REPLY_TO_MSG_ID);
    }

    public int getReplyToMsgId() {
        return replyToMsgId;
    }

    public void setReplyToMsgId(int replyToMsgId) {
        this.replyToMsgId = replyToMsgId;
        this.setFlag(FLAG_REPLY_TO_MSG_ID, replyToMsgId != 0);
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasMedia() {
        return this.isFlagSet(FLAG_MEDIA);
    }

    public TLAbsMessageMedia getMedia() {
        return media;
    }

    public void setMedia(TLAbsMessageMedia media) {
        this.media = media;
        this.setFlag(FLAG_MEDIA, media != null);
    }

    public boolean hasReplyMarkup() {
        return this.isFlagSet(FLAG_REPLY_MARKUP);
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        this.setFlag(FLAG_REPLY_MARKUP, replyMarkup != null);
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

    public boolean hasViews() {
        return this.isFlagSet(FLAG_VIEWS);
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
        this.setFlag(FLAG_VIEWS, true);
    }

    public boolean hasEditDate() {
        return this.isFlagSet(FLAG_EDIT_DATE);
    }

    public int getEditDate() {
        return editDate;
    }

    public void setEditDate(int editDate) {
        this.editDate = editDate;
        this.setFlag(FLAG_EDIT_DATE, true);
    }

    public boolean hasPostAuthor() {
        return this.isFlagSet(FLAG_POST_AUTHOR);
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
        this.setFlag(FLAG_POST_AUTHOR, this.postAuthor != null && !this.postAuthor.trim().isEmpty());
    }

    public boolean hasGroupedId() {
        return this.isFlagSet(FLAG_GROUPED_ID);
    }

    public long getGroupedId() {
        return groupedId;
    }

    public void setGroupedId(long groupedId) {
        this.groupedId = groupedId;
        this.setFlag(FLAG_GROUPED_ID, this.groupedId != 0);
    }

    public boolean hasRestrictionReasons() {
        return this.isFlagSet(FLAG_RESTRICTION_REASONS);
    }

    public TLVector<String> getRestrictionReasons() {
        return restrictionReasons;
    }

    public void setRestrictionReasons(TLVector<String> restrictionReasons) {
        this.restrictionReasons = restrictionReasons;
        this.setFlag(FLAG_RESTRICTION_REASONS, this.restrictionReasons != null && !this.restrictionReasons.isEmpty());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        if (this.hasFromId()) {
            StreamingUtils.writeInt(this.fromId, stream);
        }
        StreamingUtils.writeTLObject(this.toId, stream);
        if (this.hasFwdFrom()) {
            StreamingUtils.writeTLObject(this.fwdFrom, stream);
        }
        if (this.hasViaBotId()) {
            StreamingUtils.writeInt(this.viaBotId, stream);
        }
        if (this.hasReplyToMsgId()) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLString(this.message, stream);
        if (this.hasMedia()) {
            StreamingUtils.writeTLObject(this.media, stream);
        }
        if (this.hasReplyMarkup()) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
        if (this.hasEntities()) {
            StreamingUtils.writeTLVector(this.entities, stream);
        }
        if (this.hasViews()) {
            StreamingUtils.writeInt(this.views, stream);
        }
        if (this.hasEditDate()) {
            StreamingUtils.writeInt(this.editDate, stream);
        }
        if (this.hasPostAuthor()) {
            StreamingUtils.writeTLString(this.postAuthor, stream);
        }
        if (this.hasGroupedId()) {
            StreamingUtils.writeLong(this.groupedId, stream);
        }
        if (this.hasRestrictionReasons()) {
            StreamingUtils.writeTLVector(this.restrictionReasons, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        if (this.hasFromId()) {
            this.fromId = StreamingUtils.readInt(stream);
        }
        this.toId = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        if (this.hasFwdFrom()) {
            this.fwdFrom = StreamingUtils.readTLObject(stream, context, TLMessageFwdHeader.class);
        }
        if (this.hasViaBotId()) {
            this.viaBotId = StreamingUtils.readInt(stream);
        }
        if (this.hasReplyToMsgId()) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        this.date = StreamingUtils.readInt(stream);
        this.message = StreamingUtils.readTLString(stream);
        if (this.hasMedia()) {
            this.media = StreamingUtils.readTLObject(stream, context, TLAbsMessageMedia.class);
        }
        if (this.hasReplyMarkup()) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
        if (this.hasEntities()) {
            this.entities = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
        if (this.hasViews()) {
            this.views = StreamingUtils.readInt(stream);
        }
        if (this.hasEditDate()) {
            this.editDate = StreamingUtils.readInt(stream);
        }
        if (this.hasPostAuthor()) {
            this.postAuthor = StreamingUtils.readTLString(stream);
        }
        if (this.hasGroupedId()) {
            this.groupedId = StreamingUtils.readLong(stream);
        }
        if (this.hasRestrictionReasons()) {
            this.restrictionReasons = StreamingUtils.readTLVector(stream, context);
        }
    }

    @Override
    public String toString() {
        return "message#452c0e65";
    }

    @Override
    public String toLog() {
        String ret;
        String from = String.format("%08x", this.fromId);
        String to = this.toId != null ? this.toId.toLog() : null;
        String content = this.message;
        ret = "Message#" + String.format("%08x", this.id) + " from User#" + (from != null ? from : "---") + " to #" + (to != null ? to : "---") + " with content '" + (content != null ? StringUtils.abbreviate(content, 15) : "---") + "' (Views=" + this.views + ") " + (this.hasMedia() ? " with " + this.media.getClass().getSimpleName() : "") + ".";
        return ret;
    }

}