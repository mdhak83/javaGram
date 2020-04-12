package org.javagram.api.account.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.TLWebAuthorization;
import org.javagram.api.user.base.TLAbsUser;

/**
 * Web authorizations
 * account.webAuthorizations#ed56c9fc authorizations:Vector&lt;WebAuthorization&gt; users:Vector&lt;User&gt; = account.WebAuthorizations;
 */
public class TLAccountWebAuthorizations extends TLObject {

    /**
     * The constant CLASS_ID for this class.
     */
    public static final int CLASS_ID = 0xed56c9fc;

    /**
     * Web authorization list
     */
    private TLVector<TLWebAuthorization> authorizations;
    
    /**
     * Users
     */
    private TLVector<TLAbsUser> users;

    public TLAccountWebAuthorizations() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLWebAuthorization> getAuthorizations() {
        return this.authorizations;
    }

    public void setAuthorizations(TLVector<TLWebAuthorization> authorizations) {
        this.authorizations = authorizations;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.authorizations, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.authorizations = StreamingUtils.readTLVector(stream, context, TLWebAuthorization.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "account.webAuthorizations#ed56c9fc";
    }

}
