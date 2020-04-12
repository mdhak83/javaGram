package org.javagram.api.message.base.media;

import org.javagram.api.geo.base.point.TLAbsGeoPoint;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Indicates a live geolocation
 * messageMediaGeoLive#7c3c2609 geo:GeoPoint period:int = MessageMedia;
 */
public class TLMessageMediaGeoLive extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x7c3c2609;

    /**
     * Geolocation
     */
    private TLAbsGeoPoint geo;
    
    /**
     * Validity period of provided geolocation
     */
    private int period;

    public TLMessageMediaGeoLive() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsGeoPoint getGeo() {
        return this.geo;
    }

    public void setGeo(TLAbsGeoPoint geo) {
        this.geo = geo;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geo, stream);
        StreamingUtils.writeInt(this.period, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geo = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        this.period = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "messageMediaGeoLive#7c3c2609";
    }
    
}