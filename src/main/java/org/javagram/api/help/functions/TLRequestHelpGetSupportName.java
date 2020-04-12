package org.javagram.api.help.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.javagram.api.help.base.TLHelpSupportName;

/**
 * Get localized name of the telegram support user
 * help.getSupportName#d360e72c = help.SupportName;
 */
public class TLRequestHelpGetSupportName extends TLMethod<TLHelpSupportName> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xd360e72c;

    public TLRequestHelpGetSupportName() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLHelpSupportName deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLHelpSupportName) {
            return (TLHelpSupportName) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.help.TLSupport, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getSupportName#d360e72c";
    }

}