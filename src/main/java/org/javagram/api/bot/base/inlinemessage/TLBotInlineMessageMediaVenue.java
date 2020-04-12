package org.javagram.api.bot.base.inlinemessage;

import org.javagram.api.geo.base.point.TLAbsGeoPoint;
import org.javagram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * botInlineMessageMediaVenue
 * Send a venue
 * botInlineMessageMediaVenue#8a86659c flags:# geo:GeoPoint title:string address:string provider:string venue_id:string venue_type:string reply_markup:flags.2?ReplyMarkup = BotInlineMessage;
 */
public class TLBotInlineMessageMediaVenue extends TLAbsBotInlineMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8a86659c;

    private static final int FLAG_REPLY_MARKUP   = 0x00000004; // 2 : Inline keyboard

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
    
    /**
     * Inline keyboard
     */
    private TLAbsReplyMarkup replyMarkup;

    public TLBotInlineMessageMediaVenue() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getVenueId() {
        return venueId;
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

    public boolean hasReplyMarkup() {
        return this.isFlagSet(FLAG_REPLY_MARKUP);
    }
    
    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        this.setFlag(FLAG_REPLY_MARKUP, replyMarkup != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.geo, stream);
        StreamingUtils.writeTLString(this.title, stream);
        StreamingUtils.writeTLString(this.address, stream);
        StreamingUtils.writeTLString(this.provider, stream);
        StreamingUtils.writeTLString(this.venueId, stream);
        StreamingUtils.writeTLString(this.venueType, stream);
        if (this.isFlagSet(FLAG_REPLY_MARKUP)) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.geo = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        this.title = StreamingUtils.readTLString(stream);
        this.address = StreamingUtils.readTLString(stream);
        this.provider = StreamingUtils.readTLString(stream);
        this.venueId = StreamingUtils.readTLString(stream);
        this.venueType = StreamingUtils.readTLString(stream);
        if (this.isFlagSet(FLAG_REPLY_MARKUP)) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
    }

    @Override
    public String toString() {
        return "botInlineMessageMediaVenue#8a86659c";
    }

}