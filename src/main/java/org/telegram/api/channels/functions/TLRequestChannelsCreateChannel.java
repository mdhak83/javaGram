package org.telegram.api.channels.functions;

import org.telegram.api.updates.base.TLAbsUpdates;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.geo.base.point.input.TLAbsInputGeoPoint;

/**
 * Create a @see <a href="https://core.telegram.org/api/channel">supergroup/channel</a>.
 * channels.createChannel#3d5fb10f flags:# broadcast:flags.0?true megagroup:flags.1?true title:string about:string geo_point:flags.2?InputGeoPoint address:flags.2?string = Updates;
 */
public class TLRequestChannelsCreateChannel extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3d5fb10f;

    private static final int FLAG_BROADCAST  = 0x00000001; // 0 : Whether to create a @see <a href="https://core.telegram.org/api/channel">channel</a>
    private static final int FLAG_MEGAGROUP  = 0x00000002; // 1 : Whether to create a @see <a href="https://core.telegram.org/api/channel">supergroup</a>
    private static final int FLAG_GEO_POINT  = 0x00000004; // 2 : Geogroup location
    private static final int FLAG_ADDRESS    = 0x00000004; // 2 : Geogroup address

    /**
     * Channel title
     */
    private String title;

    /**
     * Channel description
     */
    private String about;

    /**
     * Geogroup location
     */
    private TLAbsInputGeoPoint geoPoint;

    /**
     * Geogroup address
     */
    private String address;

    public TLRequestChannelsCreateChannel() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isBroadcast() {
        return this.isFlagSet(FLAG_BROADCAST);
    }

    public void setBroadcast(boolean value) {
        this.setFlag(FLAG_BROADCAST, value);
    }

    public boolean isMegagroup() {
        return this.isFlagSet(FLAG_MEGAGROUP);
    }

    public void setMegagroup(boolean value) {
        this.setFlag(FLAG_MEGAGROUP, value);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean hasGeoPoint() {
        return (this.isFlagSet(FLAG_GEO_POINT));
    }
    
    public TLAbsInputGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(TLAbsInputGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
        this.setFlag(FLAG_GEO_POINT, this.geoPoint != null);
    }

    public boolean hasAddress() {
        return (this.isFlagSet(FLAG_ADDRESS));
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.setFlag(FLAG_ADDRESS, this.address != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.about, stream);
        if (this.hasGeoPoint()) {
            StreamingUtils.writeTLObject(this.geoPoint, stream);
        }
        if (this.hasAddress()) {
            StreamingUtils.writeTLString(this.address, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.title = StreamingUtils.readTLString(stream);
        this.about = StreamingUtils.readTLString(stream);
        if (this.hasGeoPoint()) {
            this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
        }
        if (this.hasAddress()) {
            this.address = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsUpdates.class.getName() +", got: " + res.getClass().getName());
        }
    }

    @Override
    public String toString() {
        return "channels.createChannel#3d5fb10f";
    }

}