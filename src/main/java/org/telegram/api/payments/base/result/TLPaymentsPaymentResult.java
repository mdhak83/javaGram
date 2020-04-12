package org.telegram.api.payments.base.result;

import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Payment result
 * payments.paymentResult#4e5f810d updates:Updates = payments.PaymentResult;
 */
public class TLPaymentsPaymentResult extends TLAbsPaymentsPaymentResult {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x4e5f810d;

    /**
     * Info about the payment
     */
    private TLAbsUpdates updates;

    public TLPaymentsPaymentResult() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsUpdates getUpdates() {
        return updates;
    }

    public void setUpdates(TLAbsUpdates updates) {
        this.updates = updates;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.updates, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.updates = StreamingUtils.readTLObject(stream, context, TLAbsUpdates.class);
    }

    @Override
    public String toString() {
        return "payments.paymentResult#4e5f810d";
    }

}