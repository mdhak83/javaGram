package org.telegram.api.payments.functions;

import org.telegram.api.payments.base.TLPaymentsPaymentReceipt;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPaymentsGetPaymentReceipt extends TLMethod<TLPaymentsPaymentReceipt> {
    public static final int CLASS_ID = 0xa092a980;

    private int msgId;

    public TLRequestPaymentsGetPaymentReceipt() {
        super();
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    @Override
    public TLPaymentsPaymentReceipt deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLPaymentsPaymentReceipt) {
            return (TLPaymentsPaymentReceipt) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPaymentsPaymentReceipt.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(msgId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        msgId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "payments.getPaymentReceipt#a092a980";
    }

}
