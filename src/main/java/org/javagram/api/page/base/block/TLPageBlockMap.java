package org.javagram.api.page.base.block;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.geo.base.point.TLAbsGeoPoint;
import org.javagram.api.page.base.TLPageCaption;

/**
 * A map
 * pageBlockMap#a44f3ef6 geo:GeoPoint zoom:int w:int h:int caption:PageCaption = PageBlock;
 */
public class TLPageBlockMap extends TLAbsPageBlock {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa44f3ef6;

    /**
     * Location of the map center
     */
    private TLAbsGeoPoint geo;
    
    /**
     * Map zoom level; 13-20
     */
    private int zoom;
    
    /**
     * Map width in pixels before applying scale; 16-102
     */
    private int w;
    
    /**
     * Map height in pixels before applying scale; 16-1024
     */
    private int h;
    
    /**
     * Caption
     */
    private TLPageCaption caption;

    public TLPageBlockMap() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsGeoPoint getGeo() {
        return geo;
    }

    public void setGeo(TLAbsGeoPoint geo) {
        this.geo = geo;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
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

    public TLPageCaption getCaption() {
        return caption;
    }

    public void setCaption(TLPageCaption caption) {
        this.caption = caption;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geo, stream);
        StreamingUtils.writeInt(this.zoom, stream);
        StreamingUtils.writeInt(this.w, stream);
        StreamingUtils.writeInt(this.h, stream);
        StreamingUtils.writeTLObject(this.caption, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geo = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        this.zoom = StreamingUtils.readInt(stream);
        this.w = StreamingUtils.readInt(stream);
        this.h = StreamingUtils.readInt(stream);
        this.caption = StreamingUtils.readTLObject(stream, context, TLPageCaption.class);
    }

    @Override
    public String toString() {
        return "pageBlockMap#a44f3ef6";
    }

}