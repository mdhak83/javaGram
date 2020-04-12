package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.TLChatOnlines;
import org.telegram.api.peer.base.input.TLAbsInputPeer;

/**
 * Get count of online users in a chat
 * messages.getOnlines#6e2be050 peer:InputPeer = ChatOnlines;
 */
public class TLRequestMessagesGetOnlines extends TLMethod<TLChatOnlines> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6e2be050;

    /**
     * The chat
     */
    private TLAbsInputPeer peer;

    public TLRequestMessagesGetOnlines() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
    }

    @Override
    public TLChatOnlines deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLChatOnlines) {
            return (TLChatOnlines) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getOnlines#6e2be050";
    }

}