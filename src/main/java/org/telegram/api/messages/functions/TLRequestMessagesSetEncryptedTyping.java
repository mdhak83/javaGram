package org.telegram.api.messages.functions;

import org.telegram.api.chat.base.input.encrypted.TLInputEncryptedChat;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Send typing event by the current user to a secret chat.
 * messages.setEncryptedTyping#791451ed peer:InputEncryptedChat typing:Bool = Bool;
 */
public class TLRequestMessagesSetEncryptedTyping extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x791451ed;

    /**
     * Secret chat ID
     */
    private TLInputEncryptedChat peer;

    /**
     * Typing.
     * Possible values:
     * - true if the user started typing and more than <strong>5 seconds</strong> have passed since the last request
     * - false if the user stopped typing
     */
    private boolean typing;

    public TLRequestMessagesSetEncryptedTyping() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLInputEncryptedChat getPeer() {
        return this.peer;
    }

    public void setPeer(TLInputEncryptedChat value) {
        this.peer = value;
    }

    public boolean getTyping() {
        return this.typing;
    }

    public void setTyping(boolean value) {
        this.typing = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLBool(this.typing, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputEncryptedChat.class);
        this.typing = StreamingUtils.readTLBool(stream);
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
        return "messages.setEncryptedTyping#791451ed";
    }

}