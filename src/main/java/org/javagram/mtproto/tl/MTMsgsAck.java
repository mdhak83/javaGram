package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

import static org.javagram.utils.StreamingUtils.readTLLongVector;
import static org.javagram.utils.StreamingUtils.writeTLVector;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 03.11.13
 * Time: 8:30
 */
public class MTMsgsAck extends TLObject {

    public static final int CLASS_ID = 0x62d6b459;

    private TLLongVector messages = new TLLongVector();

    public MTMsgsAck(TLLongVector messages) {
        this.messages = messages;
    }

    public MTMsgsAck() {
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

    public TLLongVector getMessages() {
        return this.messages;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeTLVector(this.messages, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.messages = readTLLongVector(stream, context);
    }

    @Override
    public String toString() {
        return "msgs_ack#62d6b459";
    }
}
