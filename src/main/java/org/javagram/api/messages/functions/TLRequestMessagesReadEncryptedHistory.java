package org.javagram.api.messages.functions;

import org.javagram.api.chat.base.input.encrypted.TLInputEncryptedChat;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Marks message history within a secret chat as read.
 * messages.readEncryptedHistory#7f4b690a peer:InputEncryptedChat max_date:int = Bool;
 */
public class TLRequestMessagesReadEncryptedHistory extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7f4b690a;

    /**
     * Secret chat ID
     */
    private TLInputEncryptedChat peer;
    
    /**
     * Maximum date value for received messages in history
     */
    private int maxDate;

    public TLRequestMessagesReadEncryptedHistory() {
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

    public int getMaxDate() {
        return this.maxDate;
    }

    public void setMaxDate(int value) {
        this.maxDate = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxDate, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLInputEncryptedChat.class);
        this.maxDate = StreamingUtils.readInt(stream);
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
        return "messages.readEncryptedHistory#7f4b690a";
    }

}