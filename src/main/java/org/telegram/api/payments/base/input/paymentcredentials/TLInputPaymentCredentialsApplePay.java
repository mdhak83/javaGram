package org.telegram.api.payments.base.input.paymentcredentials;

import org.telegram.api.json.base.TLDataJSON;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Apple pay payment credentials
 * inputPaymentCredentialsApplePay#aa1c39f payment_data:DataJSON = InputPaymentCredentials;
 */
public class TLInputPaymentCredentialsApplePay extends TLAbsInputPaymentCredentials {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaa1c39f;

    /**
     * Payment data
     */
    private TLDataJSON data;

    public TLInputPaymentCredentialsApplePay() {
        super();
    }

    public TLDataJSON getData() {
        return data;
    }

    public void setData(TLDataJSON data) {
        this.data = data;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.data, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.data = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public String toString() {
        return "inputPaymentCredentialsApplePay#aa1c39f";
    }
    
}
