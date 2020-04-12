package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * Channel/supergroup description was changed
 * channelAdminLogEventActionChangeAbout#55188a2e prev_value:string new_value:string = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionChangeAbout extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x55188a2e;
    
    /**
     * Previous description
     */
    private String prevValue;

    /**
     * New description
     */
    private String newValue;

    public TLChannelAdminLogEventActionChangeAbout() {
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
        return "channelAdminLogEventActionChangeAbout#55188a2e";
    }

}