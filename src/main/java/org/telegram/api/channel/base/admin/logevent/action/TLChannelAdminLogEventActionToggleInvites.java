package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * Invites were enabled/disabled
 * channelAdminLogEventActionToggleInvites#1b7907ae new_value:Bool = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionToggleInvites extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b7907ae;

    /**
     * New value
     */
    private boolean newValue;

    public TLChannelAdminLogEventActionToggleInvites() {
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
        return "channelAdminLogEventActionToggleInvites#1b7907ae";
    }

}