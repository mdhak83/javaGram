package org.javagram.api.help.functions;

import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Get changelog of current app
 * help.getAppChangelog#9010ef6f prev_app_version:string = Updates;
 */
public class TLRequestHelpGetAppChangelog extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9010ef6f;

    /**
     * Previous app version
     */
    private String prevAppVersion;

    public TLRequestHelpGetAppChangelog() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPrevAppVersion() {
        return prevAppVersion;
    }

    public void setPrevAppVersion(String prevAppVersion) {
        this.prevAppVersion = prevAppVersion;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.prevAppVersion, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevAppVersion = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "help.getAppChangelog#9010ef6f";
    }

}