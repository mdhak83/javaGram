package org.javagram.api.help.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Info about an update of telegram's terms of service.
 * If the terms of service are declined, then the @see <a href="https://core.telegram.org/method/account.deleteAccount">account.deleteAccount</a> method should be called with the reason "Decline ToS update"
 * help.termsOfServiceUpdate#28ecf961 expires:int terms_of_service:help.TermsOfService = help.TermsOfServiceUpdate;
 */
public class TLHelpTermsOfServiceUpdate extends TLAbsHelpTermsOfServiceUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x28ecf961;

    /**
     * New TOS updates will have to be queried using @see <a href="https://core.telegram.org/method/help.getTermsOfServiceUpdate">help.getTermsOfServiceUpdate</a> in <code>expires</code> seconds
     */
    private int expires;
    
    /**
     * New terms of service
     */
    private TLHelpTermsOfService termsOfService;

    public TLHelpTermsOfServiceUpdate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public TLHelpTermsOfService getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(TLHelpTermsOfService termsOfService) {
        this.termsOfService = termsOfService;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.expires, stream);
        StreamingUtils.writeTLObject(this.termsOfService, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.expires = StreamingUtils.readInt(stream);
        this.termsOfService = StreamingUtils.readTLObject(stream, context, TLHelpTermsOfService.class);
    }

    @Override
    public String toString() {
        return "help.termsOfServiceUpdate#28ecf961";
    }

}