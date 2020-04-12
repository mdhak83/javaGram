package org.telegram.api.account.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * Sensitive content settings
 * account.contentSettings#57e28221 flags:# sensitive_enabled:flags.0?true sensitive_can_change:flags.1?true = account.ContentSettings;
 */
public class TLAccountContentSettings extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x57e28221;

    private static final int FLAG_SENSITIVE_ENABLED     = 0x00000001; // 0 : Whether viewing of sensitive (NSFW) content is enabled
    private static final int FLAG_SENSITIVE_CAN_CHANGE  = 0x00000002; // 1 : Whether the current client can change the sensitive content settings to view NSFW content

    public TLAccountContentSettings() {
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

    public boolean isSensitiveCanChange() {
        return this.isFlagSet(FLAG_SENSITIVE_CAN_CHANGE);
    }

    public void setSensitiveCanChange(boolean value) {
        this.setFlag(FLAG_SENSITIVE_CAN_CHANGE, value);
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
    public String toString() {
        return "account.contentSettings#57e28221";
    }

}