package org.telegram.api.messages.functions;

import org.telegram.api.bot.base.inlineresult.TLAbsBotInlineResult;
import org.telegram.api.geo.base.point.input.TLAbsInputGeoPoint;
import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.api.user.base.input.TLAbsInputUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Query an inline bot
 * messages.getInlineBotResults#514e999d flags:# bot:InputUser peer:InputPeer geo_point:flags.0?InputGeoPoint query:string offset:string = messages.BotResults;
 */
public class TLRequestMessagesGetInlineBotResults extends TLMethod<TLAbsBotInlineResult> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x514e999d;

    private static final int FLAG_GEO_POINT = 0x00000001; // 0

    /**
     * The bot to query
     */
    private TLAbsInputUser bot;

    /**
     * The currently opened chat
     */
    private TLAbsInputPeer peer;

    /**
     * The geolocation, if requested
     */
    private TLAbsInputGeoPoint geoPoint;

    /**
     * The query
     */
    private String query;

    /**
     * The offset within the results, will be passed directly as-is to the bot.
     */
    private String offset;

    public TLRequestMessagesGetInlineBotResults() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputUser getBot() {
        return bot;
    }

    public void setBot(TLAbsInputUser bot) {
        this.bot = bot;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public boolean hasGeoPoint() {
        return this.isFlagSet(FLAG_GEO_POINT);
    }
    
    public TLAbsInputGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(TLAbsInputGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
        this.setFlag(FLAG_GEO_POINT, this.geoPoint != null);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.bot, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
        if (this.hasGeoPoint()) {
            StreamingUtils.writeTLObject(this.geoPoint, stream);
        }
        StreamingUtils.writeTLString(this.query, stream);
        StreamingUtils.writeTLString(this.offset, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.bot = StreamingUtils.readTLObject(stream, context, TLAbsInputUser.class);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        if (this.hasGeoPoint()) {
            this.geoPoint = StreamingUtils.readTLObject(stream, context, TLAbsInputGeoPoint.class);
        }
        this.query = StreamingUtils.readTLString(stream);
        this.offset = StreamingUtils.readTLString(stream);
    }

    @Override
    public TLAbsBotInlineResult deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsBotInlineResult) {
            return (TLAbsBotInlineResult) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLAbsBotInlineResult.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getInlineBotResults#514e999d";
    }

}