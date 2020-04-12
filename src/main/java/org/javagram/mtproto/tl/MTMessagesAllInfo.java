package org.javagram.mtproto.tl;

import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLLongVector;
import org.javagram.api._primitives.TLObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.javagram.utils.StreamingUtils.readTLLongVector;
import static org.javagram.utils.StreamingUtils.readTLString;
import static org.javagram.utils.StreamingUtils.writeTLString;
import static org.javagram.utils.StreamingUtils.writeTLVector;

/**
 * @author Ruben Bermudez
 * @version 2.0
 * @brief Voluntary Communication of Status of Messages
 * @date 20/02/15
 */
public class MTMessagesAllInfo extends TLObject {
    public static final int CLASS_ID = 0x8cc0d131;

    private TLLongVector msgIds;
    private String info;

    public MTMessagesAllInfo() {
    }

    public MTMessagesAllInfo(TLLongVector msgIds, String info) {
        this.msgIds = msgIds;
        this.info = info;
    }


    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        writeTLVector(this.msgIds, stream);
        writeTLString(this.info, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.msgIds = readTLLongVector(stream, context);
        this.info = readTLString(stream);
    }

    public TLLongVector getMsgIds() {
        return this.msgIds;
    }

    public void setMsgIds(TLLongVector msgIds) {
        this.msgIds = msgIds;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "mtMessagesAllInfo#8cc0d131";
    }
}
