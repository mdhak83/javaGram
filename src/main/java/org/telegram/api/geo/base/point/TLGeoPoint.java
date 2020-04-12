package org.telegram.api.geo.base.point;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL geo point.
 */
public class TLGeoPoint extends TLAbsGeoPoint {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x296f104;

    /**
     * Longtitude
     */
    private double _long;
    
    /**
     * Latitude
     */
    private double _lat;
    
    /**
     * Access hash
     */
    private long accessHash;

    public TLGeoPoint() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets _long.
     *
     * @return the _long
     */
    public double getLong() {
        return this._long;
    }

    /**
     * Sets _long.
     *
     * @param _long the _long
     */
    public void setLong(double _long) {
        this._long = _long;
    }

    /**
     * Gets lat.
     *
     * @return the lat
     */
    public double getLat() {
        return this._lat;
    }

    /**
     * Sets lat.
     *
     * @param _lat the lat
     */
    public void setLat(double _lat) {
        this._lat = _lat;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeDouble(this._long, stream);
        StreamingUtils.writeDouble(this._lat, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this._long = StreamingUtils.readDouble(stream);
        this._lat = StreamingUtils.readDouble(stream);
        this.accessHash = StreamingUtils.readLong(stream);
    }

    @Override
    public String toString() {
        return "geoPoint#296f104";
    }

}