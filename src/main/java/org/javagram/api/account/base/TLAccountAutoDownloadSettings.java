package org.javagram.api.account.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.TLAutoDownloadSettings;
import org.javagram.api._primitives.TLObject;

/**
 * Media autodownload settings
 * account.autoDownloadSettings#63cacf26 low:AutoDownloadSettings medium:AutoDownloadSettings high:AutoDownloadSettings = account.AutoDownloadSettings;
 */
public class TLAccountAutoDownloadSettings extends TLObject {

    /**
     * The constant CLASS_ID of this class.
     */
    public static final int CLASS_ID = 0x63cacf26;

    /**
     * Low data usage preset
     */
    private TLAutoDownloadSettings low;
    
    /**
     * Medium data usage preset
     */
    private TLAutoDownloadSettings medium;
    
    /**
     * High data usage preset
     */
    private TLAutoDownloadSettings high;
    
    public TLAccountAutoDownloadSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAutoDownloadSettings getLow() {
        return low;
    }

    public void setLow(TLAutoDownloadSettings low) {
        this.low = low;
    }

    public TLAutoDownloadSettings getMedium() {
        return medium;
    }

    public void setMedium(TLAutoDownloadSettings medium) {
        this.medium = medium;
    }

    public TLAutoDownloadSettings getHigh() {
        return high;
    }

    public void setHigh(TLAutoDownloadSettings high) {
        this.high = high;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.low, stream);
        StreamingUtils.writeTLObject(this.medium, stream);
        StreamingUtils.writeTLObject(this.high, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.low = StreamingUtils.readTLObject(stream, context, TLAutoDownloadSettings.class);
        this.medium = StreamingUtils.readTLObject(stream, context, TLAutoDownloadSettings.class);
        this.high = StreamingUtils.readTLObject(stream, context, TLAutoDownloadSettings.class);
    }

    @Override
    public String toString() {
        return "account.autoDownloadSettings#63cacf26";
    }

}