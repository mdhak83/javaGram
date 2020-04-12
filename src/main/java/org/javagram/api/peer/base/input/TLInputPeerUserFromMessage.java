package org.javagram.api.peer.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLObject;

/**
 * Defines a @see <a href="https://core.telegram.org/api/min">min</a> user that was seen in a certain message of a certain chat.
 * inputPeerUserFromMessage#17bae2e6 peer:InputPeer msg_id:int user_id:int = InputPeer;
 */
public class TLInputPeerUserFromMessage extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x17bae2e6;

    /**
     * The chat where the user was seen
     */
    private TLAbsInputPeer peer;
    
    /**
     * The message ID
     */
    private int msgId;

    /**
     * The identifier of the user that was seen
     */
    private int userId;

    public TLInputPeerUserFromMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.msgId, stream);
        StreamingUtils.writeInt(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.msgId = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputPeerUserFromMessage#17bae2e6";
    }

}