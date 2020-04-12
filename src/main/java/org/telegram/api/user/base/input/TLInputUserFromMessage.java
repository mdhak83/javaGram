package org.telegram.api.user.base.input;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.peer.base.input.TLAbsInputPeer;

/**
 * Defines a @see <a href="https://core.telegram.org/api/min">min</a> user that was seen in a certain message of a certain chat.
 * inputUserFromMessage#2d117597 peer:InputPeer msg_id:int user_id:int = InputUser;
 */
public class TLInputUserFromMessage extends TLAbsInputUser {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2d117597;

    /**
     * The chat where the user was seen
     */
    private TLAbsInputPeer peer; 
    
    /**
     * The message ID
     */
    private int messageId;

    /**
     * The identifier of the user that was seen
     */
    private int userId;
    
    public TLInputUserFromMessage() {
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

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.messageId, stream);
        StreamingUtils.writeInt(this.userId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.messageId = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputUserFromMessage#2d117597";
    }

}