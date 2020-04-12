package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.javagram.utils.StreamingUtils.readInt;
import static org.javagram.utils.StreamingUtils.readLong;
import static org.javagram.utils.StreamingUtils.writeInt;
import static org.javagram.utils.StreamingUtils.writeLong;

public class MTBadMessageNotification extends MTBadMessage {

    public static final int CLASS_ID = 0xa7eff811;

    public MTBadMessageNotification(long badMsgId, int badMsqSeqno, int errorCode) {
        this.badMsgId = badMsgId;
        this.badMsqSeqno = badMsqSeqno;
        this.errorCode = errorCode;
    }

    public MTBadMessageNotification() {

    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.badMsgId, stream);
        writeInt(this.badMsqSeqno, stream);
        writeInt(this.errorCode, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.badMsgId = readLong(stream);
        this.badMsqSeqno = readInt(stream);
        this.errorCode = readInt(stream);
    }

    @Override
    public String toString() {
        return "bad_msg_notification#" + CLASS_ID;
    }
}
