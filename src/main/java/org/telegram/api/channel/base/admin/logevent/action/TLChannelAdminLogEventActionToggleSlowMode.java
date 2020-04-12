package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * @see <a href="https://core.telegram.org/method/channels.toggleSlowMode">Slow mode setting for supergroups was changed</a>
 * channelAdminLogEventActionToggleSlowMode#53909779 prev_value:int new_value:int = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionToggleSlowMode extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x53909779;

    /**
     * Previous linked chat
     */
    private int prevValue;

    /**
     * New linked chat
     */
    private int newValue;

    public TLChannelAdminLogEventActionToggleSlowMode() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(int prevValue) {
        this.prevValue = prevValue;
    }

    public int getNewValue() {
        return newValue;
    }

    public void setNewValue(int newValue) {
        this.newValue = newValue;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.prevValue, stream);
        StreamingUtils.writeInt(this.newValue, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevValue = StreamingUtils.readInt(stream);
        this.newValue = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionToggleSlowMode#53909779";
    }

}