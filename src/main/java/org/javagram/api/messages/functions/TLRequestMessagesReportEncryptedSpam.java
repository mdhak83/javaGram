package org.javagram.api.messages.functions;

import org.javagram.api.chat.base.input.encrypted.TLInputEncryptedChat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.utils.StreamingUtils;

/**
 * Report a secret chat for spam
 * messages.reportEncryptedSpam#4b0c8c0f peer:InputEncryptedChat = Bool;
 */
public class TLRequestMessagesReportEncryptedSpam extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4b0c8c0f;

    /**
     * The secret chat to report
     */
    private TLInputEncryptedChat peer;

    public TLRequestMessagesReportEncryptedSpam() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLInputEncryptedChat getPeer() {
        return peer;
    }

    public void setPeer(TLInputEncryptedChat peer) {
        this.peer = peer;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputEncryptedChat.class);
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

    @Override
    public String toString() {
        return "messages.reportEncryptedSpam#4b0c8c0f";
    }

}