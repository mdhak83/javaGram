package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBool;

/**
 * Set sensitive content settings (for viewing or hiding NSFW content)
 * account.setContentSettings#b574b16b flags:# sensitive_enabled:flags.0?true = Bool;
 */
public class TLRequestAccountSetContentSettings extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb574b16b;

    private static final int FLAG_SENSITIVE_ENABLED = 0x00000001; // 0 : Enable NSFW content

    public TLRequestAccountSetContentSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isSensitiveEnabled() {
        return this.isFlagSet(FLAG_SENSITIVE_ENABLED);
    }
    
    public void setSensitiveEnabled(boolean value) {
        this.setFlag(FLAG_SENSITIVE_ENABLED, value);
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
        return "account.setContentSettings#b574b16b";
    }

}