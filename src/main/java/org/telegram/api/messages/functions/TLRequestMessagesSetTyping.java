package org.telegram.api.messages.functions;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.sendmessage.base.action.TLAbsSendMessageAction;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Sends a current user typing event (see @see <a href="https://core.telegram.org/type/SendMessageAction">SendMessageAction</a> for all event types) to a conversation partner or group.
 * messages.setTyping#a3825e50 peer:InputPeer action:SendMessageAction = Bool;
 */
public class TLRequestMessagesSetTyping extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa3825e50;

    /**
     * Target user or group
     */
    private TLAbsInputPeer peer;

    /**
     * Type of action
     * Parameter added in Layer 17.
     */
    private TLAbsSendMessageAction action;

    public TLRequestMessagesSetTyping() {
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

    public TLAbsSendMessageAction getAction() {
        return this.action;
    }

    public void setAction(TLAbsSendMessageAction action) {
        this.action = action;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.action, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.action = StreamingUtils.readTLObject(stream, context, TLAbsSendMessageAction.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.setTyping#a3825e50";
    }
    
}