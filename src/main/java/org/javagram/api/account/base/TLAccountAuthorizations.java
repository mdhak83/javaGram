package org.javagram.api.account.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.auth.base.authorization.TLAbsAuthAuthorization;

/**
 * Logged-in sessionss
 * account.authorizations#1250abde authorizations:Vector&lt;Authorization&gt; = account.Authorizations;
 */
public class TLAccountAuthorizations extends TLObject {

    /**
     * The constant CLASS_ID for this class.
     */
    public static final int CLASS_ID = 0x1250abde;

    /**
     * Logged-in sessions
     */
    private TLVector<TLAbsAuthAuthorization> authorizations;

    public TLAccountAuthorizations() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLAbsAuthAuthorization> getAuthorizations() {
        return this.authorizations;
    }

    public void setAuthorizations(TLVector<TLAbsAuthAuthorization> authorizations) {
        this.authorizations = authorizations;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.authorizations, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.authorizations = StreamingUtils.readTLVector(stream, context, TLAbsAuthAuthorization.class);
    }

    @Override
    public String toString() {
        return "account.authorizations#1250abde";
    }

}