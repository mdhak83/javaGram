package org.telegram.api.account.functions;

import org.telegram.api.account.base.TLAccountTmpPassword;
import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  @since 08/AUG/2016
 */
public class TLRequestGetTmpPassword extends TLMethod<TLAccountTmpPassword> {
    public static final int CLASS_ID = 0x4a82327e;

    private TLBytes passwordHash;
    private int period;

    public TLRequestGetTmpPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAccountTmpPassword deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountTmpPassword) {
            return (TLAccountTmpPassword) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLAccountTmpPassword.class.getCanonicalName() +
                ", got: " + res.getClass().getCanonicalName());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBytes(passwordHash, stream);
        StreamingUtils.writeInt(period, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        passwordHash = StreamingUtils.readTLBytes(stream, context);
        period = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "account.getTmpPassword#4a82327e";
    }

}
