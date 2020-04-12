package org.javagram.api.messages.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLIntVector;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Forwards messages by their IDs.
 * messages.forwardMessages#d9fee60e flags:# silent:flags.5?true background:flags.6?true with_my_score:flags.8?true grouped:flags.9?true from_peer:InputPeer id:Vector&lt;int&gt; random_id:Vector&lt;long&gt; to_peer:InputPeer schedule_date:flags.10?int = Updates;
 */
public class TLRequestMessagesForwardMessages extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd9fee60e;

    private static final int FLAG_SILENT           = 0x00000020; //  5 : Whether to send messages silently (no notification will be triggered on the destination clients)
    private static final int FLAG_BACKGROUND       = 0x00000040; //  6 : Whether to send the message in background
    private static final int FLAG_WITH_MY_SCORE    = 0x00000100; //  8 : When forwarding games, whether to include your score in the game
    private static final int FLAG_GROUPED          = 0x00000200; //  9 : Whether the specified messages represent an album (grouped media)
    private static final int FLAG_SCHEDULE_DATE    = 0x00000400; // 10 : Scheduled message date for scheduled messages

    /**
     * Source of messages
     */
    private TLAbsInputPeer fromPeer;

    /**
     * IDs of messages
     */
    private TLIntVector id;

    /**
     * Random ID to prevent resending of messages
     */
    private TLLongVector randomId;

    /**
     * Destination peer
     */
    private TLAbsInputPeer toPeer;
    
    /**
     * Scheduled message date for scheduled messages
     */
    private int scheduleDate;

    public TLRequestMessagesForwardMessages() {
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

    public boolean isWithMyScore() {
        return this.isFlagSet(FLAG_WITH_MY_SCORE);
    }

    public void setWithMyScore(boolean value) {
        this.setFlag(FLAG_WITH_MY_SCORE, value);
    }

    public boolean isGrouped() {
        return this.isFlagSet(FLAG_GROUPED);
    }

    public void setGrouped(boolean value) {
        this.setFlag(FLAG_GROUPED, value);
    }

    public TLAbsInputPeer getFromPeer() {
        return fromPeer;
    }

    public void setFromPeer(TLAbsInputPeer fromPeer) {
        this.fromPeer = fromPeer;
    }

    public TLIntVector getId() {
        return id;
    }

    public void setId(TLIntVector id) {
        this.id = id;
    }

    public TLLongVector getRandomId() {
        return randomId;
    }

    public void setRandomId(TLLongVector randomId) {
        this.randomId = randomId;
    }

    public TLAbsInputPeer getToPeer() {
        return toPeer;
    }

    public void setToPeer(TLAbsInputPeer toPeer) {
        this.toPeer = toPeer;
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
        StreamingUtils.writeTLObject(this.fromPeer, stream);
        StreamingUtils.writeTLVector(this.id, stream);
        StreamingUtils.writeTLVector(this.randomId, stream);
        StreamingUtils.writeTLObject(this.toPeer, stream);
        if (this.hasScheduleDate()) {
            StreamingUtils.writeInt(this.scheduleDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.fromPeer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.id = StreamingUtils.readTLIntVector(stream, context);
        this.randomId = StreamingUtils.readTLLongVector(stream, context);
        this.toPeer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
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
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.forwardMessages#d9fee60e";
    }

}