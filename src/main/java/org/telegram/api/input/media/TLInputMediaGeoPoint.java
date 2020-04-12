package org.telegram.api.input.media;

import org.telegram.api.geo.base.point.input.TLAbsInputGeoPoint;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input media geo point.
 */
public class TLInputMediaGeoPoint extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf9c44144;

    /**
     * The Geo point.
     */
    protected TLAbsInputGeoPoint geoPoint;

    public TLInputMediaGeoPoint() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets geo point.
     *
     * @return the geo point
     */
    public TLAbsInputGeoPoint getGeoPoint() {
        return this.geoPoint;
    }

    /**
     * Sets geo point.
     *
     * @param value the value
     */
    public void setGeoPoint(TLAbsInputGeoPoint value) {
        this.geoPoint = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geoPoint, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
    }

    @Override
    public String toString() {
        return "inputMediaGeoPoint#f9c44144";
    }

}