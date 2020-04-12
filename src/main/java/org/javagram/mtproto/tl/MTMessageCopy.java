package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.javagram.utils.StreamingUtils.readTLObject;
import static org.javagram.utils.StreamingUtils.writeTLObject;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief MTMessageCopy
 * @date 21/02/15
 */
public class MTMessageCopy extends TLObject {
    public static final int CLASS_ID = 0xe06046b2;

    private MTMessage orig_message;

    public MTMessageCopy() {
    }

    public MTMessageCopy(MTMessage orig_message) {
        this.orig_message = orig_message;
    }

    public MTMessage getOrig_message() {
        return orig_message;
    }

    public void setOrig_message(MTMessage orig_message) {
        this.orig_message = orig_message;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeTLObject(this.orig_message, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.orig_message = (MTMessage) readTLObject(stream, context);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "MTMessageCopy#e06046b2";
    }
}
