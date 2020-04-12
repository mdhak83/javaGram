package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * The hidden prehistory setting was @see <a href="https://core.telegram.org/method/channels.togglePreHistoryHidden">changed</a>
 * channelAdminLogEventActionTogglePreHistoryHidden#5f5c95f1 new_value:Bool = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionTogglePreHistoryHidden extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5f5c95f1;

    /**
     * New value
     */
    private boolean newValue;

    public TLChannelAdminLogEventActionTogglePreHistoryHidden() {
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
        return "channelAdminLogEventActionTogglePreHistoryHidden#5f5c95f1";
    }

}