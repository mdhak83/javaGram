package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.messages.base.TLMessagesBotCallbackAnswer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request channel edit admin
 */
public class TLRequestMessagesGetBotCallbackAnswer extends TLMethod<TLMessagesBotCallbackAnswer> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x810a9fec;

    private static final int FLAG_DATA            = 0x00000001; // 0
    private static final int FLAG_GAME          = 0x00000002; // 1

    private TLAbsInputPeer peer;
    private int msgId;
    private TLBytes data;

    public TLRequestMessagesGetBotCallbackAnswer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLMessagesBotCallbackAnswer deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesBotCallbackAnswer) {
            return (TLMessagesBotCallbackAnswer) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesBotCallbackAnswer.class.getName() +", got: " + res.getClass().getName());
        }
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public int getMsgId() {
        return msgId;
    }

    public TLBytes getData() {
        return data;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public void setData(TLBytes data) {
        this.data = data;
    }

    public void enableGame(boolean enabled) {
        this.setFlag(FLAG_GAME, enabled);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(peer, stream);
        StreamingUtils.writeInt(msgId, stream);
        if ((flags & FLAG_DATA) != 0) {
            StreamingUtils.writeTLBytes(data, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        msgId = StreamingUtils.readInt(stream);
        if ((flags & FLAG_DATA) != 0) {
            data = StreamingUtils.readTLBytes(stream, context);
        }
    }

    @Override
    public String toString() {
        return "messages.getBotCallbackAnswer#810a9fec";
    }

}