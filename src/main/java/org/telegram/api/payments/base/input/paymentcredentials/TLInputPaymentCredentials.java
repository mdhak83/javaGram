package org.telegram.api.payments.base.input.paymentcredentials;

import org.telegram.api.json.base.TLDataJSON;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Payment credentials
 * inputPaymentCredentials#3417d728 flags:# save:flags.0?true data:DataJSON = InputPaymentCredentials;
 */
public class TLInputPaymentCredentials extends TLAbsInputPaymentCredentials {
    public static final int CLASS_ID = 0x3417d728;

    private static final int FLAG_SAVE       = 0x00000001; // 0

    private int flags;
    private TLDataJSON data;

    public TLInputPaymentCredentials() {
        super();
    }

    public TLDataJSON getData() {
        return data;
    }

    public void setData(TLDataJSON data) {
        this.data = data;
    }

    public void setSave(boolean save) {
        if (save) {
            flags |= FLAG_SAVE;
        } else {
            flags &= ~FLAG_SAVE;
        }
    }

    public boolean isSave() {
        return (flags & FLAG_SAVE) != 0;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(data, stream);

    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        data = StreamingUtils.readTLObject(stream, context, TLDataJSON.class);
    }

    @Override
    public String toString() {
        return "inputPaymentCredentials#3417d728";
    }

}
