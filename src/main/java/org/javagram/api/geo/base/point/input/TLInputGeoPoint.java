package org.javagram.api.geo.base.point.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL input geo point.
 */
public class TLInputGeoPoint extends TLAbsInputGeoPoint {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf3b7acc9;

    private double lat;
    private double lon;

    public TLInputGeoPoint() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeDouble(this.lat, stream);
        StreamingUtils.writeDouble(this.lon, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.lat = StreamingUtils.readDouble(stream);
        this.lon = StreamingUtils.readDouble(stream);
    }

    @Override
    public String toString() {
        return "inputGeoPoint#f3b7acc9";
    }

}