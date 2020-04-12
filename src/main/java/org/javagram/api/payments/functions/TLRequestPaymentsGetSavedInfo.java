package org.javagram.api.payments.functions;

import org.javagram.api.payments.base.TLPaymentsSavedInfo;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLRequestPaymentsGetSavedInfo extends TLMethod<TLPaymentsSavedInfo> {
    public static final int CLASS_ID = 0x227d824b;

    public TLRequestPaymentsGetSavedInfo() {
        super();
    }

    @Override
    public TLPaymentsSavedInfo deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLPaymentsSavedInfo) {
            return (TLPaymentsSavedInfo) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLPaymentsSavedInfo.class.getName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "payments.getSavedInfo#227d824b";
    }

}
