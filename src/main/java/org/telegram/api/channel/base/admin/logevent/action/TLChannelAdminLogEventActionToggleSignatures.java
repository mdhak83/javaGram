package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * Channel signatures were enabled/disabled
 * channelAdminLogEventActionToggleSignatures#26ae0971 new_value:Bool = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionToggleSignatures extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x26ae0971;

    /**
     * New value
     */
    private boolean newValue;

    public TLChannelAdminLogEventActionToggleSignatures() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean getNewValue() {
        return newValue;
    }

    public void setNewValue(boolean newValue) {
        this.newValue = newValue;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLBool(this.newValue, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.newValue = StreamingUtils.readTLBool(stream);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionToggleSignatures#26ae0971";
    }

}