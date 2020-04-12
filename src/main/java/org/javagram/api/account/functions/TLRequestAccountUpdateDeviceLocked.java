package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * When client-side passcode lock feature is enabled, will not show message texts in incoming @see <a href="https://core.telegram.org/api/push-updates">PUSH notifications</a>.
 * account.updateDeviceLocked#38df3532 period:int = Bool;
 */
public class TLRequestAccountUpdateDeviceLocked extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x38df3532;

    /**
     * Inactivity period after which to start hiding message texts in @see <a href="https://core.telegram.org/api/push-updates">PUSH notifications</a>.
     */
    private int period;

    public TLRequestAccountUpdateDeviceLocked() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.period, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.period = StreamingUtils.readInt(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.privacy.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.updateDeviceLocked#38df3532";
    }

}