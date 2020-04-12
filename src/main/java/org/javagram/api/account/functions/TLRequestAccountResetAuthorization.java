package org.javagram.api.account.functions;

import org.javagram.api.account.base.TLAccountPrivacyRules;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Log out an active @see <a href="https://core.telegram.org/api/auth">authorized session</a> by its hash
 * account.resetAuthorization#df77f3bc hash:long = Bool;
 */
public class TLRequestAccountResetAuthorization extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdf77f3bc;

    /**
     * Session hash
     */
    private long hash;

    public TLRequestAccountResetAuthorization() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getHash() {
        return this.hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.hash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.hash = StreamingUtils.readLong(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.privacy.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.resetAuthorization#df77f3bc";
    }

}