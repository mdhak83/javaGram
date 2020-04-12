package org.javagram.api.payments.base.input.paymentcredentials;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Saved payment credentials
 * inputPaymentCredentialsSaved#c10eb2cf id:string tmp_password:bytes = InputPaymentCredentials;
 */
public class TLInputPaymentCredentialsSaved extends TLAbsInputPaymentCredentials {
    
    public static final int CLASS_ID = 0xc10eb2cf;

    private String id;
    private TLBytes tmpPassword;

    public TLInputPaymentCredentialsSaved() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TLBytes getTmpPassword() {
        return tmpPassword;
    }

    public void setTmpPassword(TLBytes tmpPassword) {
        this.tmpPassword = tmpPassword;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(id, stream);
        StreamingUtils.writeTLBytes(tmpPassword, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        id = StreamingUtils.readTLString(stream);
        tmpPassword = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "inputPaymentCredentialsSaved#c10eb2cf";
    }

}
