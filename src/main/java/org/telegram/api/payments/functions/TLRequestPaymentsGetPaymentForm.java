package org.telegram.api.payments.functions;

import org.telegram.api.payments.base.TLPaymentsPaymentForm;
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
public class TLRequestPaymentsGetPaymentForm extends TLMethod<TLPaymentsPaymentForm> {
    public static final int CLASS_ID = 0x99f09745;

    private int msgId;

    public TLRequestPaymentsGetPaymentForm() {
        super();
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    @Override
    public TLPaymentsPaymentForm deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLPaymentsPaymentForm) {
            return (TLPaymentsPaymentForm) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPaymentsPaymentForm.class.getName() +
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
        return "payments.getPaymentForm#99f09745";
    }

}
