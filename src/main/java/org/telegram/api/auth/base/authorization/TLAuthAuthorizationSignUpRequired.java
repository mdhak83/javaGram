package org.telegram.api.auth.base.authorization;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.help.base.TLHelpTermsOfService;

/**
 * An account with this phone number doesn't exist on telegram: the user has to @see <a href="https://core.telegram.org/api/auth">enter basic information and sign up</a>
 * auth.authorizationSignUpRequired#44747e9a flags:# terms_of_service:flags.0?help.TermsOfService = auth.Authorization;
 */
public class TLAuthAuthorizationSignUpRequired extends TLAbsAuthAuthorization {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x44747e9a;

    private static final int FLAG_TERMS_OF_SERVICE  = 0x00000001; // 0 : Telegram's terms of service: the user must read and accept the terms of service before signing up to telegram

    /**
     * Telegram's terms of service: the user must read and accept the terms of service before signing up to telegram
     */
    private TLHelpTermsOfService termsOfService;

    
    public TLAuthAuthorizationSignUpRequired() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasTermsOfService() {
        return this.isFlagSet(FLAG_TERMS_OF_SERVICE);
    }
    
    public TLHelpTermsOfService getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(TLHelpTermsOfService termsOfService) {
        this.termsOfService = termsOfService;
        if (this.termsOfService != null) {
            this.flags |= FLAG_TERMS_OF_SERVICE;
        } else {
            this.flags &= ~FLAG_TERMS_OF_SERVICE;
        }
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasTermsOfService()) {
            StreamingUtils.writeTLObject(this.termsOfService, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasTermsOfService()) {
            this.termsOfService = StreamingUtils.readTLObject(stream, context, TLHelpTermsOfService.class);
        }
    }

    @Override
    public String toString() {
        return "auth.authorizationSignUpRequired#44747e9a";
    }

}