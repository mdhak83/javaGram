package org.telegram.api.channel.base.participant;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.TLChatBannedRights;

/**
 * Admin
 */
public class TLChannelParticipantBanned extends TLAbsChannelParticipant {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1c0facaf;

    private static final int FLAG_LEFT  = 0x00000001; // 0

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    private int userId;
    private int kickedBy;
    private int date;
    private TLChatBannedRights bannedRights;

    public TLChannelParticipantBanned() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getKickedBy() {
        return kickedBy;
    }

    public void setKickedBy(int kickedBy) {
        this.kickedBy = kickedBy;
    }

    public TLChatBannedRights getBannedRights() {
        return bannedRights;
    }

    public void setBannedRights(TLChatBannedRights bannedRights) {
        this.bannedRights = bannedRights;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.userId, stream);
        StreamingUtils.writeInt(this.kickedBy, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.bannedRights, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        this.kickedBy = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.bannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
    }

    @Override
    public String toString() {
        return "channelParticipantBanned#1c0facaf";
    }

}
