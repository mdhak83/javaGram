package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLVector;

/**
 * Vote in a @see <a href="https://core.telegram.org/constructor/poll">poll</a>
 * messages.sendVote#10ea6184 peer:InputPeer msg_id:int options:Vector&lt;bytes&gt; = Updates;
 */
public class TLRequestMessagesSendVote extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x10ea6184;

    /**
     * The chat where the poll was sent
     */
    private TLAbsInputPeer peer;

    /**
     * The message ID of the poll
     */
    private int msgId;
    
    /**
     * The options that were chosen
     */
    private TLVector<TLBytes> options;
    
    public TLRequestMessagesSendVote() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    public void setPeer(TLAbsInputPeer value) {
        this.peer = value;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public TLVector<TLBytes> getOptions() {
        return options;
    }

    public void setOptions(TLVector<TLBytes> options) {
        this.options = options;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.msgId, stream);
        StreamingUtils.writeTLVector(this.options, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.msgId = StreamingUtils.readInt(stream);
        this.options = StreamingUtils.readTLVector(stream, context, TLBytes.class);
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
        return "messages.sendVote#10ea6184";
    }

}