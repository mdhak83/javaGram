package org.javagram.api.input.media;

import org.javagram.api.geo.base.point.input.TLAbsInputGeoPoint;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Can be used to send a venue geolocation.
 */
public class TLInputMediaVenue extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc13d1c11;

    private TLAbsInputGeoPoint inputGeoPoint;
    private String title;
    private String address;
    private String provider;
    private String venueId;
    private String venueType;

    public TLInputMediaVenue() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets input geo point.
     *
     * @return the input geo point
     */
    public TLAbsInputGeoPoint getInputGeoPoint() {
        return this.inputGeoPoint;
    }

    /**
     * Sets input geo point.
     *
     * @param inputGeoPoint the input geo point
     */
    public void setInputGeoPoint(TLAbsInputGeoPoint inputGeoPoint) {
        this.inputGeoPoint = inputGeoPoint;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets provider.
     *
     * @return the provider
     */
    public String getProvider() {
        return this.provider;
    }

    /**
     * Sets provider.
     *
     * @param provider the provider
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Gets venue id.
     *
     * @return the venue id
     */
    public String getVenueId() {
        return this.venueId;
    }

    /**
     * Sets venue id.
     *
     * @param venueId the venue id
     */
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getVenueType() {
        return this.venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeTLObject(this.inputGeoPoint, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeTLString(this.provider, stream);
        StreamingUtils.writeTLString(this.venueId, stream);
        StreamingUtils.writeTLString(this.venueType, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.inputGeoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
        this.title = StreamingUtils.readTLString(stream);
        this.address = StreamingUtils.readTLString(stream);
        this.provider = StreamingUtils.readTLString(stream);
        this.venueId = StreamingUtils.readTLString(stream);
        this.venueType = StreamingUtils.readTLString(stream);
    }

    @Override
    public String toString() {
        return "inputMediaVenue#c13d1c11";
    }

}