package org.telegram.mtproto.tl;

import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLLongVector;
import org.telegram.api._primitives.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

import static org.telegram.utils.StreamingUtils.readTLLongVector;
import static org.telegram.utils.StreamingUtils.writeTLVector;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 8:50
 */
public class MTMsgsStateReq extends TLObject {
    public static final int CLASS_ID = 0xda69fb52;

    private TLLongVector messages;

    public MTMsgsStateReq(TLLongVector messages) {
        this.messages = messages;
    }

    public MTMsgsStateReq() {
        this.messages = new TLLongVector();
    }

    public MTMsgsStateReq(long[] msgIds) {
        this.messages = new TLLongVector();
        for (long id : msgIds) {
            this.messages.add(id);
        }
    }

    public MTMsgsStateReq(Long[] msgIds) {
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
        return "msg_resend_req#da69fb52";
    }
}
