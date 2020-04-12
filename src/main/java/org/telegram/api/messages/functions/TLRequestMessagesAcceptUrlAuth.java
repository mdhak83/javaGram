package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.TLStatsURL;
import org.telegram.api.peer.base.input.TLAbsInputPeer;

/**
 * Use this to accept a Seamless Telegram Login authorization request, for more info @see <a href="https://core.telegram.org/api/url-authorization">click here »</a>
 * messages.acceptUrlAuth#f729ea98 flags:# write_allowed:flags.0?true peer:InputPeer msg_id:int button_id:int = UrlAuthResult;
 */
public class TLRequestMessagesAcceptUrlAuth extends TLMethod<TLStatsURL> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf729ea98;

    private static final int FLAG_WRITE_ALLOWED  = 0x00000001; //  0 : Set this flag to allow the bot to send messages to you (if requested)

    /**
     * The location of the message
     */
    private TLAbsInputPeer peer;
    
    /**
     * Message ID of the message with the login button
     */
    private int msgId;
    
    /**
     * ID of the login button
     */
    private int buttonId;
    
    public TLRequestMessagesAcceptUrlAuth() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isWriteAllowed() {
        return this.isFlagSet(FLAG_WRITE_ALLOWED);
    }

    public void setWriteAllowed(boolean value) {
        this.setFlag(FLAG_WRITE_ALLOWED, value);
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

    public int getButtonId() {
        return buttonId;
    }

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.msgId, stream);
        StreamingUtils.writeInt(this.buttonId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.msgId = StreamingUtils.readInt(stream);
        this.buttonId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLStatsURL deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLStatsURL) {
            return (TLStatsURL) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.acceptUrlAuth#f729ea98";
    }

}