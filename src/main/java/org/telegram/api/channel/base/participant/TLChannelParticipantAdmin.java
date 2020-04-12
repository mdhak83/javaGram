package org.telegram.api.channel.base.participant;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.chat.base.TLChatAdminRights;

/**
 * Admin
 */
public class TLChannelParticipantAdmin extends TLAbsChannelParticipant {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xccbebbaf;

    private static final int FLAG_CAN_EDIT  = 0x00000001; // 0
    private static final int FLAG_SELF      = 0x00000002; // 1
    private static final int FLAG_RANK      = 0x00000004; // 2

    private int userId;
    private int inviterId;
    private int promotedBy;
    private int date;
    private TLChatAdminRights adminRights;
    private String rank;

    public TLChannelParticipantAdmin() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getPromotedBy() {
        return promotedBy;
    }

    public void setPromotedBy(int promotedBy) {
        this.promotedBy = promotedBy;
    }

    public TLChatAdminRights getAdminRights() {
        return adminRights;
    }

    public void setAdminRights(TLChatAdminRights adminRights) {
        this.adminRights = adminRights;
    }

    public String getRank() {
        return rank;
    }

    public void setCanEdit(boolean canEdit) {
        this.setFlag(FLAG_CAN_EDIT, canEdit);
    }

    public void setSelf(boolean self) {
        this.setFlag(FLAG_RANK, self);
    }

    public void setRank(String rank) {
        this.rank = rank;
        this.flags |= FLAG_RANK;
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

    public int getInviterId() {
        return inviterId;
    }

    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
        this.flags |= FLAG_SELF;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.userId, stream);
        if ((this.flags & FLAG_SELF) != 0) {
            StreamingUtils.writeInt(this.inviterId, stream);
        }
        StreamingUtils.writeInt(this.promotedBy, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeTLObject(this.adminRights, stream);
        if ((this.flags & FLAG_RANK) != 0) {
            StreamingUtils.writeTLString(this.rank, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.userId = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_SELF) != 0) {
            this.inviterId = StreamingUtils.readInt(stream);
        }
        this.promotedBy = StreamingUtils.readInt(stream);
        this.date = StreamingUtils.readInt(stream);
        this.adminRights = StreamingUtils.readTLObject(stream, context, TLChatAdminRights.class);
        if ((this.flags & FLAG_RANK) != 0) {
            this.rank = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "channelParticipantAdmin#ccbebbaf";
    }

}
