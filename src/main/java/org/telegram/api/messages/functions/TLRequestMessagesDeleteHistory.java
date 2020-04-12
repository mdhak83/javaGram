package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.messages.base.TLAffectedHistory;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Deletes communication history.
 * messages.deleteHistory#1c015b09 flags:# just_clear:flags.0?true revoke:flags.1?true peer:InputPeer max_id:int = messages.AffectedHistory;
 */
public class TLRequestMessagesDeleteHistory extends TLMethod<TLAffectedHistory> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c015b09;

    private static final int FLAG_JUST_CLEAR = 0x00000001;  // 0 : Just clear history for the current user, without actually removing messages for every chat user
    private static final int FLAG_REVOKE = 0x00000002;      // 1 : Whether to delete the message history for all chat participants
    

    /**
     * User or chat, communication history of which will be deleted
     */
    private TLAbsInputPeer peer;

    /**
     * Maximum ID of message to delete
     */
    private int maxId;

    public TLRequestMessagesDeleteHistory() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    public boolean isJustClear() {
        return this.isFlagSet(FLAG_JUST_CLEAR);
    }

    public void setJustClear(boolean value) {
        this.setFlag(FLAG_JUST_CLEAR, value);
    }

    public boolean isRevoke() {
        return this.isFlagSet(FLAG_REVOKE);
    }

    public void setRevoke(boolean value) {
        this.setFlag(FLAG_REVOKE, value);
    }

    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.maxId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAffectedHistory deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAffectedHistory) {
            return (TLAffectedHistory) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAffectedHistory.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.deleteHistory#1c015b09";
    }

}