package org.javagram.api.message.base.action;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A payment was sent
 * messageActionPaymentSent#40699cd0 currency:string total_amount:long = MessageAction;
 */
public class TLMessageActionPaymentSent extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x40699cd0;

    /**
     * Three-letter ISO 4217 @see <a href="https://core.telegram.org/bots/payments#supported-currencies">currency</a> code
     */
    private String currency;
    
    /**
     * Price of the product in the smallest units of the currency (integer, not float/double).
     * For example, for a price of US$ 1.45 pass amount = 145.
     * See the exp parameter in @see <a href="https://core.telegram.org/bots/payments/currencies.json">currencies.json</a>, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    private long totalAmount;

    public TLMessageActionPaymentSent() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.currency, stream);
        StreamingUtils.writeLong(this.totalAmount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.currency = StreamingUtils.readTLString(stream);
        this.totalAmount = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "messageActionPaymentSent#40699cd0";
    }

}