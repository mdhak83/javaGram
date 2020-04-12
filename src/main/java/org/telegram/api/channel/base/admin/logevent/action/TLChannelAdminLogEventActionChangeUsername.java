package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * Channel/supergroup username was changed
 * channelAdminLogEventActionChangeUsername#6a4afc38 prev_value:string new_value:string = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionChangeUsername extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6a4afc38;

    /**
     * Old username
     */
    private String prevValue;

    /**
     * New username
     */
    private String newValue;

    public TLChannelAdminLogEventActionChangeUsername() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public String getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(String prevValue) {
        this.prevValue = prevValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLString(this.prevValue, stream);
        StreamingUtils.writeTLString(this.newValue, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevValue = StreamingUtils.readTLString(stream);
        this.newValue = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionChangeUsername#6a4afc38";
    }

}