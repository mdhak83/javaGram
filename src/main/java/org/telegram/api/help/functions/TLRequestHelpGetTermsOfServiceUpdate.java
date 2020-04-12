package org.telegram.api.help.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.help.base.TLAbsHelpTermsOfServiceUpdate;

/**
 * Look for updates of telegram's terms of service
 * help.getTermsOfServiceUpdate#2ca51fd1 = help.TermsOfServiceUpdate;
 */
public class TLRequestHelpGetTermsOfServiceUpdate extends TLMethod<TLAbsHelpTermsOfServiceUpdate> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2ca51fd1;
    
    public TLRequestHelpGetTermsOfServiceUpdate() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAbsHelpTermsOfServiceUpdate deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsHelpTermsOfServiceUpdate) {
            return (TLAbsHelpTermsOfServiceUpdate) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLAbsAppUpdate, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getTermsOfServiceUpdate#2ca51fd1";
    }

}