package org.telegram.api.channel.base.participant;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Channel/supergroup creator
 */
public class TLChannelParticipantCreator extends TLAbsChannelParticipant {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x808d15a4;

    private static final int FLAG_RANK         = 0x00000001; // 0

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * User ID
     */
    private int userId;
    
    /**
     * The role (rank) of the group creator in the group: just an arbitrary string, admin by default
     */
    private String rank;

    public TLChannelParticipantCreator() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.userId, stream);
        if ((this.flags & FLAG_RANK) != 0) {
            StreamingUtils.writeTLString(this.rank, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.userId = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_RANK) != 0) {
            this.rank = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "channelParticipantCreator#808d15a4";
    }

}