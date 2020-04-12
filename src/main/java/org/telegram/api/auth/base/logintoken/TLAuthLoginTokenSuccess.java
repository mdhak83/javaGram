package org.telegram.api.auth.base.logintoken;

import org.telegram.api.auth.base.logintoken.TLAbsAuthLoginToken;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.auth.base.authorization.TLAbsAuthAuthorization;

/**
 * Login via token (QR code) succeded!
 * auth.loginTokenSuccess#390d5c5e authorization:auth.Authorization = auth.LoginToken;
 */
public class TLAuthLoginTokenSuccess extends TLAbsAuthLoginToken {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x390d5c5e;

    /**
     * Authorization info
     */
    private TLAbsAuthAuthorization authorization;
    
    public TLAuthLoginTokenSuccess() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsAuthAuthorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(TLAbsAuthAuthorization authorization) {
        this.authorization = authorization;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.authorization, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.authorization = StreamingUtils.readTLObject(stream, context, TLAbsAuthAuthorization.class);
    }

    @Override
    public String toString() {
        return "auth.loginTokenSuccess#390d5c5e";
    }

}