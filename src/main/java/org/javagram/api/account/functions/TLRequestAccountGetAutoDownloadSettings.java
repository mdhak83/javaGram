package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.javagram.api.account.base.TLAccountAutoDownloadSettings;

/**
 * Get media autodownload settings
 * account.getAutoDownloadSettings#56da0b3f = account.AutoDownloadSettings;
 */
public class TLRequestAccountGetAutoDownloadSettings extends TLMethod<TLAccountAutoDownloadSettings> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x56da0b3f;

    public TLRequestAccountGetAutoDownloadSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLAccountAutoDownloadSettings deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAccountAutoDownloadSettings) {
            return (TLAccountAutoDownloadSettings) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getAutoDownloadSettings#56da0b3f";
    }

}