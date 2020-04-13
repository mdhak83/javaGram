package org.javagram.api.chat.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * channelForbidden
 * Indicates a channel/supergroup we can't access because we were banned, or for some other reason.
 * channelForbidden#289da732 flags:# broadcast:flags.5?true megagroup:flags.8?true id:int access_hash:long title:string until_date:flags.16?int = Chat;
 */
public class TLChannelForbidden extends TLAbsChat {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x289da732;

    private static final int FLAG_BROADCAST     = 0x00000020; // 05 : Is this a channel
    private static final int FLAG_MEGAGROUP     = 0x00000100; // 08 : Is this a supergroup
    private static final int FLAG_UNTIL_DATE    = 0x00010000; // 16 : The ban is valid until the specified date

    /**
     * Title
     */
    private String title;

    /**
     * The ban is valid until the specified date
     */
    private int untilDate;

    public TLChannelForbidden() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public boolean isChannel() {
        return true;
    }

    @Override
    public Long getAccessHash() {
        return null;
    }

    public boolean isBroadcast() {
        return this.isFlagSet(FLAG_BROADCAST);
    }

    public void setBroadcast(boolean value) {
        this.setFlag(FLAG_UNTIL_DATE, value);
    }
    
    public boolean isMegagroup() {
        return this.isFlagSet(FLAG_MEGAGROUP);
    }

    public void setMegagroup(boolean value) {
        this.setFlag(FLAG_MEGAGROUP, value);
    }
    
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasUntilDate() {
        return this.isFlagSet(FLAG_UNTIL_DATE);
    }

    public int getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(int untilDate) {
        this.untilDate = untilDate;
        this.setFlag(FLAG_UNTIL_DATE, untilDate != 0);
    }
    
    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeTLString(this.title, stream);
        if (this.hasUntilDate()) {
            StreamingUtils.writeInt(this.untilDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readInt(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.title = StreamingUtils.readTLString(stream);
        if (this.hasUntilDate()) {
            this.untilDate = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "channelForbidden#289da732";
    }

    @Override
    public String toLog() {
        return ("ChannelForbidden: " + this.title + " (id=" + String.format("%08x", this.id) + ";accessHash=" + String.format("%016x", this.accessHash) + ")");
    }
    
}