package org.javagram.api.payment.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TLPaymentCharge extends TLObject {
    public static final int CLASS_ID = 0xea02c27e;

    private String id;
    private String providerChargeId;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getId() {
        return id;
    }

    public String getProviderChargeId() {
        return providerChargeId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLString(providerChargeId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLString(stream);
        providerChargeId = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "paymentCharge#ea02c27e";
    }

}
