package org.telegram.api.messages.base;

import org.telegram.api.bot.base.TLInlineBotSwitchPm;
import org.telegram.api.bot.base.inlineresult.TLAbsBotInlineResult;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.user.base.TLAbsUser;

/**
 * Result of a query to an inline bot
 */
public class TLBotResults extends TLObject {
    public static final int CLASS_ID = 0x947ca848;

    private static final int FLAG_GALLERY     = 0x00000001; // 0
    private static final int FLAG_NEXT_OFFSET = 0x00000002; // 1
    private static final int FLAG_SWITCH_PM   = 0x00000004; // 2

    private long queryId;
    private String nextOffset;
    private TLInlineBotSwitchPm switchPm;
    private TLVector<TLAbsBotInlineResult> results;
    private int cacheTime;
    private TLVector<TLAbsUser> users;

    public TLBotResults() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getQueryId() {
        return queryId;
    }

    public String getNextOffset() {
        return nextOffset;
    }

    public TLVector<TLAbsBotInlineResult> getResults() {
        return results;
    }

    public TLInlineBotSwitchPm getSwitchPm() {
        return switchPm;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public boolean isGallery() {
        return (flags & FLAG_GALLERY) != 0;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }

    public void setNextOffset(String nextOffset) {
        this.nextOffset = nextOffset;
    }

    public void setSwitchPm(TLInlineBotSwitchPm switchPm) {
        this.switchPm = switchPm;
    }

    public void setResults(TLVector<TLAbsBotInlineResult> results) {
        this.results = results;
    }

    public void setCacheTime(int cacheTime) {
        this.cacheTime = cacheTime;
    }

    public TLVector<TLAbsUser> getUsers() {
        return users;
    }

    public void setUsers(TLVector<TLAbsUser> users) {
        this.users = users;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.queryId, stream);
        if ((this.flags & FLAG_NEXT_OFFSET) != 0) {
            StreamingUtils.writeTLString(this.nextOffset, stream);
        }
        if ((this.flags & FLAG_SWITCH_PM) != 0) {
            StreamingUtils.writeTLObject(this.switchPm, stream);
        }
        StreamingUtils.writeTLVector(this.results, stream);
        StreamingUtils.writeInt(this.cacheTime, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.queryId = StreamingUtils.readLong(stream);
        if ((this.flags & FLAG_NEXT_OFFSET) != 0) {
            this.nextOffset = StreamingUtils.readTLString(stream);
        }
        if ((this.flags & FLAG_SWITCH_PM) != 0) {
            this.switchPm = StreamingUtils.readTLObject(stream, context, TLInlineBotSwitchPm.class);
        }
        this.results = StreamingUtils.readTLVector(stream, context, TLAbsBotInlineResult.class);
        this.cacheTime = StreamingUtils.readInt(stream);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.botResults#947ca848";
    }

}
