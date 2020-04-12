package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.TLAutoDownloadSettings;
import org.javagram.api._primitives.TLBool;

/**
 * Change media autodownload settings
 * account.saveAutoDownloadSettings#76f36233 flags:# low:flags.0?true high:flags.1?true settings:AutoDownloadSettings = Bool;
 */
public class TLRequestAccountSaveAutoDownloadSettings extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x76f36233;

    private static final int FLAG_LOW   = 0x00000001; // 0 : Whether to save settings in the low data usage preset
    private static final int FLAG_HIGH  = 0x00000002; // 1 : Whether to save settings in the high data usage preset

    /**
     * Media autodownload settings
     */
    private TLAutoDownloadSettings settings;

    public TLRequestAccountSaveAutoDownloadSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isLow() {
        return this.isFlagSet(FLAG_LOW);
    }

    public void setLow(boolean value) {
        this.setFlag(FLAG_LOW, value);
    }

    public boolean isHigh() {
        return this.isFlagSet(FLAG_HIGH);
    }

    public void setHigh(boolean value) {
        this.setFlag(FLAG_HIGH, value);
    }

    public TLAutoDownloadSettings getSettings() {
        return settings;
    }

    public void setSettings(TLAutoDownloadSettings settings) {
        this.settings = settings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.settings = StreamingUtils.readTLObject(stream, context, TLAutoDownloadSettings.class);
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
        return "account.saveAutoDownloadSettings#76f36233";
    }

}