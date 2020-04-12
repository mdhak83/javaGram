package org.javagram.api.auth.functions;

import org.javagram.api.auth.base.TLPasswordRecovery;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Request recovery code of a @see <a href="https://core.telegram.org/api/srp">2FA password</a>, only for accounts with a @see <a href="https://core.telegram.org/api/srp#email-verification">recovery email configured</a>.
 * auth.requestPasswordRecovery#d897bc66 = auth.PasswordRecovery;
 */
public class TLRequestAuthRequestPasswordRecovery extends TLMethod<TLPasswordRecovery> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd897bc66;

    public TLRequestAuthRequestPasswordRecovery() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLPasswordRecovery deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLPasswordRecovery) {
            return (TLPasswordRecovery) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.auth.TLPasswordRecovery, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.requestPasswordRecovery#d897bc66";
    }

}