package org.javagram.api.account.functions;

import org.javagram.api.account.base.TLAccountPasswordInputSettings;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.password.base.input.checkpassword.TLAbsInputCheckPasswordSRP;

/**
 * Set a new 2FA password
 * account.updatePasswordSettings#a59b102f password:InputCheckPasswordSRP new_settings:account.PasswordInputSettings = Bool;
 */
public class TLRequestAccountUpdatePasswordSettings extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa59b102f;

    /**
     * The old password (see @see <a href="https://core.telegram.org/api/srp">SRP</a>)
     */
    private TLAbsInputCheckPasswordSRP password;

    /**
     * The new password (see @see <a href="https://core.telegram.org/api/srp">SRP</a>)
     */
    private TLAccountPasswordInputSettings newSettings;

    public TLRequestAccountUpdatePasswordSettings() {
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

    public TLAccountPasswordInputSettings getNewSettings() {
        return newSettings;
    }

    public void setNewSettings(TLAccountPasswordInputSettings newSettings) {
        this.newSettings = newSettings;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.password, stream);
        StreamingUtils.writeTLObject(this.newSettings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.password = StreamingUtils.readTLObject(stream, context, TLAbsInputCheckPasswordSRP.class);
        this.newSettings = StreamingUtils.readTLObject(stream, context, TLAccountPasswordInputSettings.class);
    }

    @Override
    public String toString() {
        return "account.updatePasswordSettings#a59b102f";
    }

}