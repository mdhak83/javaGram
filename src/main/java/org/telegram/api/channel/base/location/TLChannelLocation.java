package org.telegram.api.channel.base.location;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.geo.base.point.TLAbsGeoPoint;

public class TLChannelLocation extends TLAbsChannelLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x209b82db;

    private TLAbsGeoPoint geoPoint;
    private String address;

    public TLChannelLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(TLAbsGeoPoint geoPoint) {
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
        StreamingUtils.writeTLObject(this.geoPoint, stream);
        StreamingUtils.writeTLString(this.address, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        this.address = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "channelLocation#209b82db";
    }

}