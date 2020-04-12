package org.telegram.api.bot.base.inlinemessage;

import org.telegram.api.geo.base.point.TLAbsGeoPoint;
import org.telegram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * botInlineMessageMediaGeo
 * Send a geolocation
 * botInlineMessageMediaGeo#b722de65 flags:# geo:GeoPoint period:int reply_markup:flags.2?ReplyMarkup = BotInlineMessage;
 */
public class TLBotInlineMessageMediaGeo extends TLAbsBotInlineMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb722de65;

    private static final int FLAG_REPLY_MARKUP   = 0x00000004; // 2 : Inline keyboard

    /**
     * Geolocation
     */
    private TLAbsGeoPoint geoPoint;

    /**
     * Validity period
     */
    private int period;
    
    /**
     * Inline keyboard
     */
    private TLAbsReplyMarkup replyMarkup;

    public TLBotInlineMessageMediaGeo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(TLAbsGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.geoPoint, stream);
        StreamingUtils.writeInt(this.period, stream);
        if (this.isFlagSet(FLAG_REPLY_MARKUP)) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsGeoPoint.class);
        this.period = StreamingUtils.readInt(stream);
        if (this.isFlagSet(FLAG_REPLY_MARKUP)) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
    }

    @Override
    public String toString() {
        return "botInlineMessageMediaGeo#b722de65";
    }

}