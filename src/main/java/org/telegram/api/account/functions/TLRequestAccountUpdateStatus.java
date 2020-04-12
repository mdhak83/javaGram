package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBool;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Updates online user status.
 * account.updateStatus#6628562c offline:Bool = Bool;
 */
public class TLRequestAccountUpdateStatus extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6628562c;

    /**
     * If (@see <a href="https://core.telegram.org/constructor/boolTrue">boolTrue</a>) is transmitted, user status will change to (@see <a href="https://core.telegram.org/constructor/userStatusOffline">userStatusOffline</a>).
     */
    private boolean offline;

    public TLRequestAccountUpdateStatus() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean getOffline() {
        return this.offline;
    }

    public void setOffline(boolean value) {
        this.offline = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBool(this.offline, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.offline = StreamingUtils.readTLBool(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.updateStatus#6628562c";
    }

}