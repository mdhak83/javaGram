package org.javagram.api.help.functions;

import org.javagram.api.help.base.TLHelpSupport;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns the support user for the 'ask a question' feature.
 * help.getSupport#9cdf08cd = help.Support;
 */
public class TLRequestHelpGetSupport extends TLMethod<TLHelpSupport> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cdf08cd;

    public TLRequestHelpGetSupport() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLHelpSupport deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null)
            throw new IOException("Unable to parse response");
        if (res instanceof TLHelpSupport)
            return (TLHelpSupport) res;
        throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLSupport, got: " + res.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        return "help.getSupport#9cdf08cd";
    }

}