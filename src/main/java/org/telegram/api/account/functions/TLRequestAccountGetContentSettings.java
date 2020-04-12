package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.account.base.TLAccountContentSettings;

/**
 * Get sensitive content settings
 * account.getContentSettings#8b9b4dae = account.ContentSettings;
 */
public class TLRequestAccountGetContentSettings extends TLMethod<TLAccountContentSettings> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8b9b4dae;

    public TLRequestAccountGetContentSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAccountContentSettings deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountContentSettings) {
            return (TLAccountContentSettings) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getContentSettings#8b9b4dae";
    }

}