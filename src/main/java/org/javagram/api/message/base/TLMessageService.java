package org.javagram.api.message.base;

import org.javagram.api.message.base.action.TLAbsMessageAction;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.client.handlers.AbstractUpdatesHandler;

/**
 * Indicates a service message
 * messageService#9e19a1f6 flags:# out:flags.1?true mentioned:flags.4?true media_unread:flags.5?true silent:flags.13?true post:flags.14?true legacy:flags.19?true id:int from_id:flags.8?int to_id:Peer reply_to_msg_id:flags.3?int date:int action:MessageAction = Message;
 */
public class TLMessageService extends TLAbsMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9e19a1f6;

    private static final int FLAG_OUT              = 0x00000002; // 1
    private static final int FLAG_REPLY_TO_MSG_ID  = 0x00000008; // 3
    private static final int FLAG_MENTIONED        = 0x00000010; // 4
    private static final int FLAG_MEDIA_UNREAD     = 0x00000020; // 5
    private static final int FLAG_FROM_ID          = 0x00000100; // 8
    private static final int FLAG_SILENT           = 0x00002000; // 13
    private static final int FLAG_POST             = 0x00004000; // 14
    private static final int FLAG_LEGACY           = 0x00080000; // 19
    
    /**
     * Message ID
     */
    private int id;
    
    /**
     * Id of te sender of the message
     */
    private int fromId;
    
    /**
     * ID of the destination of the message
     */
    private TLAbsPeer toId;
    
    /**
     * ID of the message this message replies to
     */
    private int replyToMsgId;
    
    /**
     * Message date
     */
    private int date;
    
    /**
     * Event connected with the service message
     */
    private TLAbsMessageAction action;

    public TLMessageService() {
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

    public boolean isLegacy() {
        return this.isFlagSet(FLAG_LEGACY);
    }

    public void setLegacy(boolean value) {
        this.setFlag(FLAG_LEGACY, value);
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasFromId() {
        return this.isFlagSet(FLAG_FROM_ID);
    }

    public int getFromId() {
        return this.fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
        this.setFlag(FLAG_FROM_ID, this.fromId != 0);
    }

    @Override
    public int getChatId() {
        return getToId().getId();
    }

    public TLAbsPeer getToId() {
        return this.toId;
    }

    public void setToId(TLAbsPeer toId) {
        this.toId = toId;
    }

    public boolean hasReplyToMsgId() {
        return this.isFlagSet(FLAG_REPLY_TO_MSG_ID);
    }

    public int getReplyToMsgId() {
        return replyToMsgId;
    }

    public void setReplyToMsgId(int replyToMsgId) {
        this.replyToMsgId = replyToMsgId;
        this.setFlag(FLAG_REPLY_TO_MSG_ID, this.replyToMsgId != 0);
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public TLAbsMessageAction getAction() {
        return this.action;
    }

    public void setAction(TLAbsMessageAction action) {
        this.action = action;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        if (this.hasFromId()) {
            StreamingUtils.writeInt(this.fromId, stream);
        }
        StreamingUtils.writeTLObject(this.toId, stream);
        if (this.hasReplyToMsgId()) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        if (this.hasFromId()) {
            this.fromId = StreamingUtils.readInt(stream);
        }
        this.toId = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        if (this.hasReplyToMsgId()) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        this.date = StreamingUtils.readInt(stream);
        this.action = StreamingUtils.readTLObject(stream, context, TLAbsMessageAction.class);
    }

    @Override
    public String toString() {
        return "messageService#9e19a1f6";
    }

    @Override
    public String toLog() {
        String ret;
        String sTo = this.toId != null ? this.toId.toLog() : null;
        String sAction = this.action != null ? this.action.toLog() : null;
        ret = "MessageService#" + this.id + " : from User #" + String.format("%08x", this.fromId) + " to " + (sTo != null ? sTo : "---") + "' with content '" + (sAction != null ? sAction : "---") + "'";
        return ret;
    }

}