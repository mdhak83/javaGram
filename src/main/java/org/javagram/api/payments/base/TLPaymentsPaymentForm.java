package org.javagram.api.payments.base;

import org.javagram.api.json.base.TLDataJSON;
import org.javagram.api.payment.base.TLInvoice;
import org.javagram.api.payment.base.TLPaymentRequestedInfo;
import org.javagram.api.payment.base.savedcredentails.TLAbsPaymentSavedCredentials;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentsPaymentForm extends TLObject {
    public static final int CLASS_ID = 0x3f56aea3;

    private static final int FLAG_SAVED_INFO             = 0x00000001; // 0
    private static final int FLAG_SAVED_CREDENTIALS      = 0x00000002; // 1
    private static final int FLAG_CAN_SAVE_CREDENTIALS   = 0x00000004; // 2
    private static final int FLAG_PASSWORD_MISSING       = 0x00000008; // 3
    private static final int FLAG_NATIVE                 = 0x00000010; // 4

    private int botId;
    private TLInvoice invoice;
    private int providerId;
    private String url;
    private String nativeProvider;
    private TLDataJSON nativeParams;
    private TLPaymentRequestedInfo savedInfo;
    private TLAbsPaymentSavedCredentials savedCredentials;
    private TLVector<TLAbsUser> users;

    public TLPaymentsPaymentForm() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
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

    public String getUrl() {
        return url;
    }

    public String getNativeProvider() {
        return nativeProvider;
    }

    public TLDataJSON getNativeParams() {
        return nativeParams;
    }

    public TLPaymentRequestedInfo getSavedInfo() {
        return savedInfo;
    }

    public TLAbsPaymentSavedCredentials getSavedCredentials() {
        return savedCredentials;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public boolean canSaveCredentials() {
        return (flags & FLAG_CAN_SAVE_CREDENTIALS) != 0;
    }

    public boolean isPasswordMissing() {
        return (flags & FLAG_PASSWORD_MISSING) != 0;
    }

    public boolean isNative() {
        return (flags & FLAG_NATIVE) != 0;
    }

    public boolean hasSavedInfo() {
        return (flags & FLAG_SAVED_INFO) != 0;
    }

    public boolean hasSavedCredentials() {
        return (flags & FLAG_SAVED_CREDENTIALS) != 0;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(botId, stream);
        StreamingUtils.writeTLObject(invoice, stream);
        StreamingUtils.writeInt(providerId, stream);
        StreamingUtils.writeTLString(url, stream);
        if (isNative()) {
            StreamingUtils.writeTLString(nativeProvider, stream);
            StreamingUtils.writeTLObject(nativeParams, stream);
        }
        if (hasSavedInfo()) {
            StreamingUtils.writeTLObject(savedInfo, stream);
        }
        if (hasSavedCredentials()) {
            StreamingUtils.writeTLObject(savedCredentials, stream);
        }
        StreamingUtils.writeTLVector(users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        botId = StreamingUtils.readInt(stream);
        invoice = StreamingUtils.readTLObject(stream, context, TLInvoice.class);
        providerId = StreamingUtils.readInt(stream);
        url = StreamingUtils.readTLString(stream);
        if (isNative()) {
            nativeProvider = StreamingUtils.readTLString(stream);
            nativeParams = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
        }
        if (hasSavedInfo()) {
            savedInfo = StreamingUtils.readTLObject(stream, context, TLPaymentRequestedInfo.class);
        }
        if (hasSavedCredentials()) {
            savedCredentials = StreamingUtils.readTLObject(stream, context, TLAbsPaymentSavedCredentials.class);
        }
        users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);

    }

    @Override
    public String toString() {
        return "payments.paymentForm#3f56aea3";
    }

}
