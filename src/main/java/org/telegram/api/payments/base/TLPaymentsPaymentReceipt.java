package org.telegram.api.payments.base;

import org.telegram.api.payment.base.TLInvoice;
import org.telegram.api.payment.base.TLPaymentRequestedInfo;
import org.telegram.api.payment.base.TLShippingOption;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentsPaymentReceipt extends TLObject {
    public static final int CLASS_ID = 0x500911e1;

    private static final int FLAG_INFO              = 0x00000001; // 0
    private static final int FLAG_SHIPPING_OPTIONS  = 0x00000002; // 1

    private int date;
    private int botId;
    private TLInvoice invoice;
    private int providerId;
    private TLPaymentRequestedInfo info;
    private TLShippingOption shipping;
    private String currency;
    private long totalAmount;
    private String credentialsTitle;
    private TLVector<TLAbsUser> users;

    public TLPaymentsPaymentReceipt() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getDate() {
        return date;
    }

    public int getBotId() {
        return botId;
    }

    public TLInvoice getInvoice() {
        return invoice;
    }

    public int getProviderId() {
        return providerId;
    }

    public TLPaymentRequestedInfo getInfo() {
        return info;
    }

    public TLShippingOption getShipping() {
        return shipping;
    }

    public String getCurrency() {
        return currency;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public String getCredentialsTitle() {
        return credentialsTitle;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public boolean hasInfo() {
        return (flags & FLAG_INFO) != 0;
    }

    public boolean hasShippingInfo() {
        return (flags & FLAG_SHIPPING_OPTIONS) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(date, stream);
        StreamingUtils.writeInt(botId, stream);
        StreamingUtils.writeTLObject(invoice, stream);
        StreamingUtils.writeInt(providerId, stream);
        if (hasInfo()) {
            StreamingUtils.writeTLObject(info, stream);
        }
        if (hasShippingInfo()) {
            StreamingUtils.writeTLObject(shipping, stream);
        }
        StreamingUtils.writeTLString(currency, stream);
        StreamingUtils.writeLong(totalAmount, stream);
        StreamingUtils.writeTLString(credentialsTitle, stream);
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        date = StreamingUtils.readInt(stream);
        botId = StreamingUtils.readInt(stream);
        invoice = StreamingUtils.readTLObject(stream, context, TLInvoice.class);
        providerId = StreamingUtils.readInt(stream);
        if (hasInfo()) {
            info = StreamingUtils.readTLObject(stream, context, TLPaymentRequestedInfo.class);
        }
        if (hasShippingInfo()) {
            shipping = StreamingUtils.readTLObject(stream, context, TLShippingOption.class);
        }
        currency = StreamingUtils.readTLString(stream);
        totalAmount = StreamingUtils.readLong(stream);
        credentialsTitle = StreamingUtils.readTLString(stream);
        users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "payments.paymentReceipt#500911e1";
    }

}
