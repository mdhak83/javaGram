package org.javagram.api.payments.base.result;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Payment was not successful, additional verification is needed
 * payments.paymentVerificationNeeded#d8411139 url:string = payments.PaymentResult;
 */
public class TLPaymentsPaymentVerificationNeeded extends TLAbsPaymentsPaymentResult {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd8411139;

    /**
     * URL for additional payment credentials verification
     */
    private String url;

    public TLPaymentsPaymentVerificationNeeded() {
        super();
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.url, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.url = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "payments.paymentVerificationNeeded#d8411139";
    }

}