package org.javagram.api.payments.base.input.paymentcredentials;

import org.javagram.api.json.base.TLDataJSON;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Android pay payment credentials
 * inputPaymentCredentialsAndroidPay#ca05d50e payment_token:DataJSON google_transaction_id:string = InputPaymentCredentials;
 */
public class TLInputPaymentCredentialsAndroidPay extends TLAbsInputPaymentCredentials {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xca05d50e ;

    /**
     * Android pay payment token
     */
    private TLDataJSON paymentToken;

    /**
     * Android pay payment token
     */
    private String googleTransactionId;

    public TLInputPaymentCredentialsAndroidPay() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.paymentToken, stream);
        StreamingUtils.writeTLString(this.googleTransactionId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.paymentToken = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
        this.googleTransactionId = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputPaymentCredentialsAndroidPay#ca05d50e ";
    }

}
