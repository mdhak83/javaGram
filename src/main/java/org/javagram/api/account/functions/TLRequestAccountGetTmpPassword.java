package org.javagram.api.account.functions;

import org.javagram.api.account.base.TLAccountDaysTTL;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.document.base.input.TLAbsInputDocument;
import org.javagram.api.password.base.input.checkpassword.TLInputCheckPasswordSRP;
import org.javagram.api.theme.base.input.TLInputThemeSettings;

/**
 * Get temporary payment password
 * account.getTmpPassword#449e0b51 password:InputCheckPasswordSRP period:int = account.TmpPassword;
 */
public class TLRequestAccountGetTmpPassword extends TLMethod<TLAccountDaysTTL> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x449e0b51;

    /**
     * SRP password parameters
     */
    private TLInputCheckPasswordSRP password;
    
    /**
     * Time during which the temporary password will be valid, in seconds; should be between 60 and 86400
     */
    private int period;

    public TLRequestAccountGetTmpPassword() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLInputCheckPasswordSRP getPassword() {
        return password;
    }

    public void setPassword(TLInputCheckPasswordSRP password) {
        this.password = password;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    
    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.password, stream);
        StreamingUtils.writeInt(this.period, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.password = StreamingUtils.readTLObject(stream, context, TLInputCheckPasswordSRP.class);
        this.period = StreamingUtils.readInt(stream);
    }

    @Override
    public TLAccountDaysTTL deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountDaysTTL) {
            return (TLAccountDaysTTL) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getTmpPassword#449e0b51";
    }

}