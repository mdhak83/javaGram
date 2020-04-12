package org.javagram.api.message.base.action;

import org.javagram.api.payment.base.TLPaymentCharge;
import org.javagram.api.payment.base.TLPaymentRequestedInfo;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A user just sent a payment to me (a bot)
 * messageActionPaymentSentMe#8f31b327 flags:# currency:string total_amount:long payload:bytes info:flags.0?PaymentRequestedInfo shipping_option_id:flags.1?string charge:PaymentCharge = MessageAction;
 */
public class TLMessageActionPaymentSentMe extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8f31b327;

    private static final int FLAG_INFO                     = 0x00000001; // 0
    private static final int FLAG_SHIPPING_OPTION_ID       = 0x00000002; // 1
    
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
    
    /**
     * Bot specified invoice payload
     */
    private TLBytes payload;
    
    /**
     * Order info provided by the user
     */
    private TLPaymentRequestedInfo info;
    
    /**
     * Identifier of the shipping option chosen by the user
     */
    private String shippingOptionId;
    
    /**
     * Provider payment identifier
     */
    private TLPaymentCharge charge;

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

    public TLBytes getPayload() {
        return payload;
    }

    public void setPayload(TLBytes payload) {
        this.payload = payload;
    }

    public TLPaymentRequestedInfo getInfo() {
        return info;
    }

    public void setInfo(TLPaymentRequestedInfo info) {
        this.info = info;
    }

    public String getShippingOptionId() {
        return shippingOptionId;
    }

    public void setShippingOptionId(String shippingOptionId) {
        this.shippingOptionId = shippingOptionId;
    }

    public TLPaymentCharge getCharge() {
        return charge;
    }

    public void setCharge(TLPaymentCharge charge) {
        this.charge = charge;
    }

    public boolean hasInfo() {
        return (flags & FLAG_INFO) != 0;
    }

    public boolean hasShippingOption() {
        return (flags & FLAG_SHIPPING_OPTION_ID) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.currency, stream);
        StreamingUtils.writeLong(this.totalAmount, stream);
        StreamingUtils.writeTLBytes(this.payload, stream);
        if (this.hasInfo()) {
            StreamingUtils.writeTLObject(this.info, stream);
        }
        if (this.hasShippingOption()) {
            StreamingUtils.writeTLString(this.shippingOptionId, stream);
        }
        StreamingUtils.writeTLObject(this.charge, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.currency = StreamingUtils.readTLString(stream);
        this.totalAmount = StreamingUtils.readLong(stream);
        this.payload = StreamingUtils.readTLBytes(stream, context);
        if (this.hasInfo()) {
            this.info = StreamingUtils.readTLObject(stream, context, TLPaymentRequestedInfo.class);
        }
        if (this.hasShippingOption()) {
            this.shippingOptionId = StreamingUtils.readTLString(stream);
        }
        this.charge = StreamingUtils.readTLObject(stream, context, TLPaymentCharge.class);
    }

    @Override
    public String toString() {
        return "messageActionPaymentSentMe#8f31b327";
    }

}