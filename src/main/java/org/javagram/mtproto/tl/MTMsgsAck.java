package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import org.javagram.utils.StreamingUtils;

public class MTMsgsAck extends TLObject {

    public static final int CLASS_ID = 0x62d6b459;

    private TLLongVector messages = new TLLongVector();

    public MTMsgsAck() { }

    public MTMsgsAck(TLLongVector messages) {
        this.messages = messages;
    }

    public MTMsgsAck(long[] msgIds) {
        this.messages = new TLLongVector();
        for (long id : msgIds) {
            this.messages.add(id);
        }
    }

    public MTMsgsAck(Long[] msgIds) {
        this.messages = new TLLongVector();
        Collections.addAll(this.messages, msgIds);
    }

    public MTMsgsAck(Long msgId) {
        this.messages = new TLLongVector();
        this.messages.add(msgId);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLLongVector getMessages() {
        return this.messages;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.messages, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.messages = StreamingUtils.readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "msgs_ack#62d6b459";
    }
}
