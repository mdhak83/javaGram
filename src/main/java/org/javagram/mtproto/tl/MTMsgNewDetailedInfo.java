package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.javagram.utils.StreamingUtils.readInt;
import static org.javagram.utils.StreamingUtils.readLong;
import static org.javagram.utils.StreamingUtils.writeInt;
import static org.javagram.utils.StreamingUtils.writeLong;

/**
 * Created with IntelliJ IDEA.
 * User: Ruben Bermudez
 * Date: 07.11.13
 * Time: 8:37
 */
public class MTMsgNewDetailedInfo extends TLObject {

    public static final int CLASS_ID = 0x809db6df;

    private long answerMsgId;
    private int bytes;
    private int status;

    public MTMsgNewDetailedInfo(long answerMsgId, int bytes, int status) {
        this.answerMsgId = answerMsgId;
        this.bytes = bytes;
        this.status = status;
    }

    public MTMsgNewDetailedInfo() {

    }

    public long getAnswerMsgId() {
        return this.answerMsgId;
    }

    public int getBytes() {
        return this.bytes;
    }

    public int getStatus() {
        return this.status;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeLong(this.answerMsgId, stream);
        writeInt(this.bytes, stream);
        writeInt(this.status, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.answerMsgId = readLong(stream);
        this.bytes = readInt(stream);
        this.status = readInt(stream);
    }

    @Override
    public String toString() {
        return "msg_new_detailed_info#809db6df";
    }
}
