package org.javagram.api.contacts.functions;

import org.javagram.api.contacts.base.toppeers.TLAbsContactsTopPeers;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.geo.base.point.input.TLAbsInputGeoPoint;
import org.javagram.api.updates.base.TLAbsUpdates;

/**
 * Get contacts near you
 * contacts.getLocated#a356056 geo_point:InputGeoPoint = Updates;
 */
public class TLRequestContactsGetLocated extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa356056;

    /**
     * Geolocation
     */
    private TLAbsInputGeoPoint geoPoint;
    
    public TLRequestContactsGetLocated() {
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

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.geoPoint, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsContactsTopPeers.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "contacts.getLocated#a356056";
    }

}