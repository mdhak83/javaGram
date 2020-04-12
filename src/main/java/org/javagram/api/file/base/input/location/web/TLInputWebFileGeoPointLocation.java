package org.javagram.api.file.base.input.location.web;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.geo.base.point.input.TLAbsInputGeoPoint;

/**
 * Geolocation
 * inputWebFileGeoPointLocation#9f2221c9 geo_point:InputGeoPoint access_hash:long w:int h:int zoom:int scale:int = InputWebFileLocation;
 */
public class TLInputWebFileGeoPointLocation extends TLAbsInputWebFileLocation {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9f2221c9;

    /**
     * Geolocation
     */
    private TLAbsInputGeoPoint geoPoint;
    
    /**
     * Access hash
     */
    private long accessHash;
    
    /**
     * Map width in pixels before applying scale; 16-1024
     */
    private int w;
    
    /**
     * Map height in pixels before applying scale; 16-1024
     */
    private int h;
    
    /**
     * Map zoom level; 13-20
     */
    private int zoom;
    
    /**
     * Map scale; 1-3
     */
    private int scale;

    public TLInputWebFileGeoPointLocation() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(TLAbsInputGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geoPoint, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
        StreamingUtils.writeInt(this.zoom, stream);
        StreamingUtils.writeInt(this.scale, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
        this.accessHash = StreamingUtils.readLong(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
        this.zoom = StreamingUtils.readInt(stream);
        this.scale = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputWebFileGeoPointLocation#9f2221c9";
    }

}