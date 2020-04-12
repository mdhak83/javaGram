package org.javagram.api.input.media;

import org.javagram.api.geo.base.point.input.TLAbsInputGeoPoint;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Live geographical location
 */
public class TLInputMediaGeoLive extends TLAbsInputMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xce4e82fd;

    private static final int FLAG_STOPPED   = 0x00000001; // 0
    private static final int FLAG_PERIOD    = 0x00000002; // 1

    /**
     * The Geo point.
     */
    private TLAbsInputGeoPoint geoPoint;
    
    /**
     * Validity period of the current location
     */
    private int period;

    public TLInputMediaGeoLive() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isStopped() {
        return this.isFlagSet(FLAG_STOPPED);
    }
    
    public void setStopped(boolean value) {
        this.setFlag(FLAG_STOPPED, value);
    }
    
    public boolean hasPeriod() {
        return this.isFlagSet(FLAG_PERIOD);
    }
    
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
        this.setFlag(FLAG_PERIOD, period != 0);
    }

    public TLAbsInputGeoPoint getGeoPoint() {
        return this.geoPoint;
    }

    public void setGeoPoint(TLAbsInputGeoPoint value) {
        this.geoPoint = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geoPoint, stream);
        if (this.hasPeriod()) {
            StreamingUtils.writeInt(this.period, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
        if (this.hasPeriod()) {
            this.period = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "inputMediaGeoLive#ce4e82fd";
    }

}