package org.telegram.api.message.base.media;

import org.telegram.api.geo.base.point.TLAbsGeoPoint;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Venue
 * messageMediaVenue#2ec0533f geo:GeoPoint title:string address:string provider:string venue_id:string venue_type:string = MessageMedia;
 */
public class TLMessageMediaVenue extends TLAbsMessageMedia {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x2ec0533f;

    /**
     * Geolocation of venue
     */
    private TLAbsGeoPoint geo;
    
    /**
     * Venue name
     */
    private String title;
    
    /**
     * Address
     */
    private String address;
    
    /**
     * Venue provider: currently only "foursquare" needs to be supported
     */
    private String provider;
    
    /**
     * Venue ID in the provider's database
     */
    private String venueId;
    
    /**
     * Venue type in the provider's database
     */
    private String venueType;

    public TLMessageMediaVenue() {
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geo, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeTLString(this.provider, stream);
        StreamingUtils.writeTLString(this.venueId, stream);
        StreamingUtils.writeTLString(this.venueType, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geo = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        this.title = StreamingUtils.readTLString(stream);
        this.address = StreamingUtils.readTLString(stream);
        this.provider = StreamingUtils.readTLString(stream);
        this.venueId = StreamingUtils.readTLString(stream);
        this.venueType = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "messageMediaVenue#2ec0533f";
    }

}