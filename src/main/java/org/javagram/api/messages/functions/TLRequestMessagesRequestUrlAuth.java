package org.javagram.api.messages.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.auth.base.urlauthresult.TLAbsUrlAuthResult;
import org.javagram.api.peer.base.input.TLAbsInputPeer;

/**
 * Get more info about a Seamless Telegram Login authorization request, for more info @see <a href="https://core.telegram.org/api/url-authorization">click here »</a>
 * messages.requestUrlAuth#e33f5613 peer:InputPeer msg_id:int button_id:int = UrlAuthResult;
 */
public class TLRequestMessagesRequestUrlAuth extends TLMethod<TLAbsUrlAuthResult> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe33f5613;

    /**
     * Peer where the message is located
     */
    private TLAbsInputPeer peer;

    /**
     * The message
     */
    private int msgId;

    /**
     * The ID of the button with the authorization request
     */
    private int buttonId;

    public TLRequestMessagesRequestUrlAuth() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return this.peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getMsgId() {
        return this.msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getButtonId() {
        return this.buttonId;
    }

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.msgId, stream);
        StreamingUtils.writeInt(this.buttonId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.msgId = StreamingUtils.readInt(stream);
        this.buttonId = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAbsUrlAuthResult deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUrlAuthResult) {
            return (TLAbsUrlAuthResult) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.encrypted.chat.TLAbsEncryptedChat, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.requestUrlAuth#e33f5613";
    }

}