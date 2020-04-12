package org.javagram.api.messages.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.input.TLInputSingleMedia;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.api._primitives.TLVector;

/**
 * Send an album of media
 * messages.sendMultiMedia#cc0110cb flags:# silent:flags.5?true background:flags.6?true clear_draft:flags.7?true peer:InputPeer reply_to_msg_id:flags.0?int multi_media:Vector&lt;InputSingleMedia&gt; schedule_date:flags.10?int = Updates;
 */
public class TLRequestMessagesSendMultimedia extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xcc0110cb;

    private static final int FLAG_REPLY_TO_MSG_ID   = 0x00000001; //  0 : ID of the message this message should reply to
    private static final int FLAG_SILENT            = 0x00000020; //  5 : Whether to send the message silently (no notification will be triggered on the other client)
    private static final int FLAG_BACKGROUND        = 0x00000040; //  6 : Whether to send the message in background
    private static final int FLAG_CLEAR_DRAFT       = 0x00000080; //  7 : Whether to clear the @see <a href="https://core.telegram.org/api/drafts">draft</a>
    private static final int FLAG_SCHEDULE_DATE     = 0x00000400; // 10 : Scheduled message date for scheduled messages

    /**
     * The destination chat
     */
    private TLAbsInputPeer peer;

    /**
     * The message to reply to
     */
    private int replyToMsgId;
    
    /**
     * The medias to send
     */
    private TLVector<TLInputSingleMedia> multiMedia;

    /**
     * Scheduled message date for scheduled messages
     */
    private int scheduleDate;

    public TLRequestMessagesSendMultimedia() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isSilent() {
        return this.isFlagSet(FLAG_SILENT);
    }

    public void setSilent(boolean value) {
        this.setFlag(FLAG_SILENT, value);
    }

    public boolean isBackground() {
        return this.isFlagSet(FLAG_BACKGROUND);
    }

    public void setBackground(boolean value) {
        this.setFlag(FLAG_BACKGROUND, value);
    }

    public boolean isClearDraft() {
        return this.isFlagSet(FLAG_CLEAR_DRAFT);
    }

    public void setClearDraft(boolean value) {
        this.setFlag(FLAG_CLEAR_DRAFT, value);
    }

    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
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

    public TLVector<TLInputSingleMedia> getMultiMedia() {
        return multiMedia;
    }

    public void setMultiMedia(TLVector<TLInputSingleMedia> multiMedia) {
        this.multiMedia = multiMedia;
    }

    public boolean hasScheduleDate() {
        return this.isFlagSet(FLAG_SCHEDULE_DATE);
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
        if (this.hasReplyToMsgId()) {
            StreamingUtils.writeInt(this.replyToMsgId, stream);
        }
        StreamingUtils.writeTLVector(this.multiMedia, stream);
        if (this.hasScheduleDate()) {
            StreamingUtils.writeInt(this.scheduleDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        if (this.hasReplyToMsgId()) {
            this.replyToMsgId = StreamingUtils.readInt(stream);
        }
        this.multiMedia = StreamingUtils.readTLVector(stream, context, TLInputSingleMedia.class);
        if (this.hasScheduleDate()) {
            this.scheduleDate = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.sendMultiMedia#cc0110cb";
    }

}