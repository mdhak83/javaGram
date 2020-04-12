package org.javagram.api.messages.functions;

import org.javagram.api.bot.base.TLInlineBotSwitchPm;
import org.javagram.api.bot.base.input.inlineresult.TLAbsInputBotInlineResult;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL request messages create chat.
 */
public class TLRequestMessagesSetInlineBotResults extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xeb5ea206;

    private static final int FLAG_GALLERY     = 0x00000001; // 0 : Set this flag if the results are composed of media files
    private static final int FLAG_PRIVATE     = 0x00000002; // 1 : Set this flag if results may be cached on the server side only for the user that sent the query. By default, results may be returned to any user who sends the same query
    private static final int FLAG_NEXT_OFFSET = 0x00000004; // 2 : Pass the offset that a client should send in the next query with the same text to receive more results. Pass an empty string if there are no more results or if you don‘t support pagination. Offset length can’t exceed 64 bytes.
    private static final int FLAG_SWITCH_PM   = 0x00000008; // 3 : If passed, clients will display a button with specified text that switches the user to a private chat with the bot and sends the bot a start message with a certain parameter.

    /**
     * Unique identifier for the answered query
     */
    private long queryId;

    /**
     * Vector of results for the inline query
     */
    private TLVector<TLAbsInputBotInlineResult> results;

    /**
     * The maximum amount of time in seconds that the result of the inline query may be cached on the server. Defaults to 300.
     */
    private int cacheTime;

    /**
     * Pass the offset that a client should send in the next query with the same text to receive more results. Pass an empty string if there are no more results or if you don‘t support pagination. Offset length can’t exceed 64 bytes.
     */
    private String nextOffset;

    /**
     * If passed, clients will display a button with specified text that switches the user to a private chat with the bot and sends the bot a start message with a certain parameter.
     */
    private TLInlineBotSwitchPm switchPm;

    public TLRequestMessagesSetInlineBotResults() {
        super();
    }

    public boolean hasGallery() {
        return this.isFlagSet(FLAG_GALLERY);
    }
    
    public void setGallery(boolean value) {
        this.setFlag(FLAG_GALLERY, value);
    }

    public boolean hasPrivate() {
        return this.isFlagSet(FLAG_PRIVATE);
    }
    
    public void setPrivate(boolean value) {
        this.setFlag(FLAG_PRIVATE, value);
    }

    public long getQueryId() {
        return queryId;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public TLVector<TLAbsInputBotInlineResult> getResults() {
        return results;
    }

    public void setResults(TLVector<TLAbsInputBotInlineResult> results) {
        this.results = results;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public boolean hasNextOffset() {
        return this.isFlagSet(FLAG_NEXT_OFFSET);
    }
    
    public String getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(String nextOffset) {
        this.nextOffset = nextOffset;
        this.setFlag(FLAG_NEXT_OFFSET, this.nextOffset != null && !this.nextOffset.trim().isEmpty());
    }

    public boolean hasSwitchPm() {
        return this.isFlagSet(FLAG_SWITCH_PM);
    }
    
    public TLInlineBotSwitchPm getSwitchPm() {
        return switchPm;
    }

    public void setSwitchPm(TLInlineBotSwitchPm switchPm) {
        this.switchPm = switchPm;
        this.setFlag(FLAG_SWITCH_PM, this.switchPm != null);
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.queryId, stream);
        StreamingUtils.writeTLVector(this.results, stream);
        StreamingUtils.writeInt(this.cacheTime, stream);
        if (this.hasNextOffset()) {
            StreamingUtils.writeTLString(this.nextOffset, stream);
        }
        if (this.hasSwitchPm()) {
            StreamingUtils.writeTLObject(this.switchPm, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.queryId = StreamingUtils.readLong(stream);
        this.results = StreamingUtils.readTLVector(stream, context, TLAbsInputBotInlineResult.class);
        this.cacheTime = StreamingUtils.readInt(stream);
        if (this.hasNextOffset()) {
            this.nextOffset = StreamingUtils.readTLString(stream);
        }
        if (this.hasSwitchPm()) {
            this.switchPm = StreamingUtils.readTLObject(stream, context, TLInlineBotSwitchPm.class);
        }
    }

    @Override
    public String toString() {
        return "messages.setInlineBotResults#eb5ea206";
    }

}