package org.javagram.api.messages.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Send a result obtained using @see <a href="https://core.telegram.org/method/messages.getInlineBotResults">messages.getInlineBotResults</a>.
 * messages.sendInlineBotResult#220815b0 flags:# silent:flags.5?true background:flags.6?true clear_draft:flags.7?true hide_via:flags.11?true peer:InputPeer reply_to_msg_id:flags.0?int random_id:long query_id:long id:string schedule_date:flags.10?int = Updates;
 */
public class TLRequestMessagesSendInlineBotResults extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x220815b0;

    private static final int FLAG_REPLY_TO_MSG_ID   = 0x00000001; //  0 : ID of the message this message should reply to
    private static final int FLAG_SILENT            = 0x00000020; //  5 : Whether to send the message silently (no notification will be triggered on the other client)
    private static final int FLAG_BACKGROUND        = 0x00000040; //  6 : Whether to send the message in background
    private static final int FLAG_CLEAR_DRAFT       = 0x00000080; //  7 : Whether to clear the @see <a href="https://core.telegram.org/api/drafts">draft</a>
    private static final int FLAG_SCHEDULE_DATE     = 0x00000400; // 10 : Scheduled message date for scheduled messages
    private static final int FLAG_HIDE_VIA          = 0x00000800; // 11 : Whether to hide the <code>via @botname</code> in the resulting message (only for bot usernames encountered in the @see <a href="https://core.telegram.org/constructor/config">config</a>)

    /**
     * Destination
     */
    private TLAbsInputPeer peer;

    /**
     * ID of the message this message should reply to
     */
    private int replyToMsgId;

    /**
     * Random ID to avoid resending the same query
     */
    private long randomId;

    /**
     * Query ID from @see <a href="https://core.telegram.org/method/messages.getInlineBotResults">messages.getInlineBotResults</a>
     */
    private long queryId;

    /**
     * Result ID from @see <a href="https://core.telegram.org/method/messages.getInlineBotResults">messages.getInlineBotResults</a>
     */
    private String id;

    /**
     * Scheduled message date for scheduled messages
     */
    private int scheduleDate;

    public TLRequestMessagesSendInlineBotResults() {
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

    public boolean isHideVia() {
        return this.isFlagSet(FLAG_HIDE_VIA);
    }

    public void setHideVia(boolean value) {
        this.setFlag(FLAG_HIDE_VIA, value);
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
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

    public long getRandomId() {
        return randomId;
    }

    public void setRandomId(long randomId) {
        this.randomId = randomId;
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        StreamingUtils.writeLong(this.randomId, stream);
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeTLString(id, stream);
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
        this.randomId = StreamingUtils.readLong(stream);
        this.queryId = StreamingUtils.readLong(stream);
        this.id = StreamingUtils.readTLString(stream);
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
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.sendInlineBotResult#220815b0";
    }

}