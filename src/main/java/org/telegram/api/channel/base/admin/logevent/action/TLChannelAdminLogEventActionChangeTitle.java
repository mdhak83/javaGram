package org.telegram.api.channel.base.admin.logevent.action;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;

/**
 * Channel/supergroup title was changed
 * channelAdminLogEventActionChangeTitle#e6dfb825 prev_value:string new_value:string = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionChangeTitle extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe6dfb825;

    /**
     * Previous title
     */
    private String prevValue;

    /**
     * New title
     */
    private String newValue;

    public TLChannelAdminLogEventActionChangeTitle() {
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
        return "channelAdminLogEventActionChangeTitle#e6dfb825";
    }

}