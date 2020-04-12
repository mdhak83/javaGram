package org.javagram.api.bot.base.input.inlinemessage;

import org.javagram.api.geo.base.point.input.TLAbsInputGeoPoint;
import org.javagram.api.keyboard.base.replymarkup.TLAbsReplyMarkup;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 */
public class TLInputBotInlineMessageMediaGeo extends TLAbsInputBotInlineMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc1b15d65;

    private static final int FLAG_REPLY_MARKUP  = 0x00000004; // 2

    private TLAbsInputGeoPoint geoPoint;
    private int period;
    private TLAbsReplyMarkup replyMarkup;

    public TLInputBotInlineMessageMediaGeo() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public void setGeoPoint(TLAbsInputGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setReplyMarkup(TLAbsReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }
    public TLAbsInputGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public TLAbsReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.geoPoint, stream);
        StreamingUtils.writeInt(this.period, stream);
        if ((this.flags & FLAG_REPLY_MARKUP) != 0) {
            StreamingUtils.writeTLObject(this.replyMarkup, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
        this.period = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_REPLY_MARKUP) != 0) {
            this.replyMarkup = StreamingUtils.readTLObject(stream, context, TLAbsReplyMarkup.class);
        }
    }

    @Override
    public String toString() {
        return "inputBotInlineMessageMediaGeo#c1b15d65";
    }

}
