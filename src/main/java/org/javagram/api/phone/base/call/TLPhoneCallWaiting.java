package org.javagram.api.phone.base.call;

import org.javagram.api.phone.base.TLPhoneCallProtocol;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Incoming phone call
 * phoneCallWaiting#1b8f4ad1 flags:# video:flags.5?true id:long access_hash:long date:int admin_id:int participant_id:int protocol:PhoneCallProtocol receive_date:flags.0?int = PhoneCall;
 */
public class TLPhoneCallWaiting extends TLAbsPhoneCall {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x1b8f4ad1;

    private static final int FLAG_RECEIVE_DATE  = 0x00000001; // 0
    private static final int FLAG_VIDEO         = 0x00000020; // 5

    /**
     * Call ID
     */
    private long id;
    
    /**
     * Access hash
     */
    private long accessHash;
    
    /**
     * Date
     */
    private int date;
    
    /**
     * Admin ID
     */
    private int adminId;
    
    /**
     * Participant ID
     */
    private int participantId;
    
    /**
     * Phone call protocol info
     */
    private TLPhoneCallProtocol protocol;
    
    /**
     * When was the phone call received
     */
    private int receiveDate;

    public TLPhoneCallWaiting() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(long accessHash) {
        this.accessHash = accessHash;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TLPhoneCallProtocol protocol) {
        this.protocol = protocol;
    }

    public int getReceiveDate() {
        return receiveDate;
    }

    public boolean hasReceiveDate() {
        return this.isFlagSet(FLAG_RECEIVE_DATE);
    }
    
    public void setReceiveDate(int receiveDate) {
        this.receiveDate = receiveDate;
        if (this.receiveDate != 0) {
            this.flags |= FLAG_RECEIVE_DATE;
        } else {
            this.flags &= ~FLAG_RECEIVE_DATE;
        }
    }

    public void setVideo(boolean value) {
        this.setFlag(FLAG_VIDEO, value);
    }
    
    /**
     * 
     * @return True if this is a video call, false otherwise
     */
    public boolean isVideo() {
        return this.isFlagSet(FLAG_VIDEO);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(id, stream);
        StreamingUtils.writeLong(accessHash, stream);
        StreamingUtils.writeInt(date, stream);
        StreamingUtils.writeInt(adminId, stream);
        StreamingUtils.writeInt(participantId, stream);
        StreamingUtils.writeTLObject(protocol, stream);
        if ((flags & FLAG_RECEIVE_DATE) != 0) {
            StreamingUtils.writeInt(receiveDate, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        flags = StreamingUtils.readInt(stream);
        id = StreamingUtils.readLong(stream);
        accessHash = StreamingUtils.readLong(stream);
        date = StreamingUtils.readInt(stream);
        adminId = StreamingUtils.readInt(stream);
        participantId = StreamingUtils.readInt(stream);
        protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
        if ((flags & FLAG_RECEIVE_DATE) != 0) {
            receiveDate = StreamingUtils.readInt(stream);
        }
    }

    @Override
    public String toString() {
        return "phoneCallWaiting#1b8f4ad1";
    }

}