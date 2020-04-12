package org.javagram.api.account.functions;

import org.javagram.api.account.base.TLAccountPasswordSettings;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.password.base.input.checkpassword.TLAbsInputCheckPasswordSRP;

/**
 * Get private info associated to the password info (recovery email, telegram @see <a href="https://core.telegram.org/passport">passport</a> info &amp; so on)
 * account.getPasswordSettings#9cd4eaf9 password:InputCheckPasswordSRP = account.PasswordSettings;
 */
public class TLRequestAccountGetPasswordSettings extends TLMethod<TLAccountPasswordSettings> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cd4eaf9;

    /**
     * The password (see @see <a href="https://core.telegram.org/api/srp">SRP</a>)
     */
    private TLAbsInputCheckPasswordSRP password;

    public TLRequestAccountGetPasswordSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputCheckPasswordSRP getPassword() {
        return password;
    }

    public void setPassword(TLAbsInputCheckPasswordSRP password) {
        this.password = password;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.password, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.password = StreamingUtils.readTLObject(stream, context, TLAbsInputCheckPasswordSRP.class);
    }

    @Override
    public TLAccountPasswordSettings deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountPasswordSettings) {
            return (TLAccountPasswordSettings) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLAccountPasswordSettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getPasswordSettings#9cd4eaf9";
    }

}