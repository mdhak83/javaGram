package org.javagram.api.message.base.media;

import org.javagram.api.geo.base.point.TLAbsGeoPoint;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Attached map.
 * messageMediaGeo#56e0d474 geo:GeoPoint = MessageMedia;
 */
public class TLMessageMediaGeo extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x56e0d474;

    /**
     * GeoPoint
     */
    private TLAbsGeoPoint geo;

    public TLMessageMediaGeo() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geo, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geo = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
    }

    @Override
    public String toString() {
        return "messageMediaGeo#56e0d474";
    }
    
}