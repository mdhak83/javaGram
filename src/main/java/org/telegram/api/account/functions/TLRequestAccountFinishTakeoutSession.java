package org.telegram.api.account.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLBool;

/**
 * Finish account takeout session
 * account.finishTakeoutSession#1d2652ee flags:# success:flags.0?true = Bool;
 */
public class TLRequestAccountFinishTakeoutSession extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1d2652ee;

    private static final int FLAG_SUCCESS   = 0x00000001; // 0 : Data exported successfully

    public TLRequestAccountFinishTakeoutSession() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isSuccess() {
        return this.isFlagSet(FLAG_SUCCESS);
    }

    public void setSuccess(boolean value) {
        this.setFlag(FLAG_SUCCESS, value);
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.finishTakeoutSession#1d2652ee";
    }

}