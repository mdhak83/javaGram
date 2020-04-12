package org.javagram.api.channels.functions;

import org.javagram.api.channel.base.input.TLAbsInputChannel;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.geo.base.point.input.TLAbsInputGeoPoint;
import org.javagram.api._primitives.TLBool;

/**
 * Edit location of geogroup
 * channels.editLocation#58e63f6d channel:InputChannel geo_point:InputGeoPoint address:string = Bool;
 */
public class TLRequestChannelsEditLocation extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x58e63f6d;

    /**
     * @see <a href="https://core.telegram.org/api/channel">Grogroup</a>
     */
    private TLAbsInputChannel channel;

    /**
     * New geolocation
     */
    private TLAbsInputGeoPoint geoPoint;

    /**
     * Address string
     */
    private String address;

    public TLRequestChannelsEditLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public TLAbsInputGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(TLAbsInputGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
        StreamingUtils.writeTLObject(this.geoPoint, stream);
        StreamingUtils.writeTLString(this.address, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.channel = StreamingUtils.readTLObject(stream, context,TLAbsInputChannel.class);
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
        this.address = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.editLocation#58e63f6d";
    }

}