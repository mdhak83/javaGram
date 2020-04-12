package org.javagram.api.messages.functions;

import org.javagram.api.peer.base.input.TLAbsInputPeer;
import org.javagram.api.message.base.entity.TLAbsMessageEntity;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages get chats.
 */
public class TLRequestMessagesSaveDraft extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbc39e14b;

    private static final int FLAG_REPLY_TO_MSG_ID = 0x00000001; // 0
    private static final int FLAG_NO_WEBPAGE      = 0x00000002; // 1
    private static final int FLAG_ENTITIES        = 0x00000008; // 3

    private int replyToMsgId;
    private TLAbsInputPeer peer;
    private String message;
    private TLVector<TLAbsMessageEntity> entitites;

    public TLRequestMessagesSaveDraft() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    public int getReplyToMsgId() {
        return replyToMsgId;
    }

    public void setReplyToMsgId(int replyToMsgId) {
        flags |= FLAG_REPLY_TO_MSG_ID;
        this.replyToMsgId = replyToMsgId;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TLVector<TLAbsMessageEntity> getEntitites() {
        return entitites;
    }

    public void setEntitites(TLVector<TLAbsMessageEntity> entitites) {
        flags |= FLAG_ENTITIES;
        this.entitites = entitites;
    }

    public boolean hasWebPreview() {
        return (flags & FLAG_NO_WEBPAGE) == 0;
    }

    public void disableWebPreview() {
        flags |= FLAG_NO_WEBPAGE;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            StreamingUtils.writeInt(replyToMsgId, stream);
        }
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeTLString(message, stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            StreamingUtils.writeTLVector(entitites, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        if ((flags & FLAG_REPLY_TO_MSG_ID) != 0) {
            replyToMsgId = StreamingUtils.readInt(stream);
        }
        peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        message = StreamingUtils.readTLString(stream);
        if ((flags & FLAG_ENTITIES) != 0) {
            entitites = StreamingUtils.readTLVector(stream, context, TLAbsMessageEntity.class);
        }
    }

    @Override
    public String toString() {
        return "messages.saveDraft#bc39e14b";
    }

}