package org.javagram.api.channel.base.admin.logevent.action;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.channel.base.location.TLAbsChannelLocation;
import org.javagram.utils.StreamingUtils;

/**
 * The geogroup location was changed
 * channelAdminLogEventActionChangeLocation#e6b76ae prev_value:ChannelLocation new_value:ChannelLocation = ChannelAdminLogEventAction;
 */
public class TLChannelAdminLogEventActionChangeLocation extends TLAbsChannelAdminLogEventAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe6b76ae;

    /**
     * Previous location
     */
    private TLAbsChannelLocation prevValue;

    /**
     * New location
     */
    private TLAbsChannelLocation newValue;

    public TLChannelAdminLogEventActionChangeLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsChannelLocation getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(TLAbsChannelLocation prevValue) {
        this.prevValue = prevValue;
    }

    public TLAbsChannelLocation getNewValue() {
        return newValue;
    }

    public void setNewValue(TLAbsChannelLocation newValue) {
        this.newValue = newValue;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.prevValue, stream);
        StreamingUtils.writeTLObject(this.newValue, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.prevValue = StreamingUtils.readTLObject(stream, context, TLAbsChannelLocation.class);
        this.newValue = StreamingUtils.readTLObject(stream, context, TLAbsChannelLocation.class);
    }

    @Override
    public String toString() {
        return "channelAdminLogEventActionChangeLocation#e6b76ae";
    }

}