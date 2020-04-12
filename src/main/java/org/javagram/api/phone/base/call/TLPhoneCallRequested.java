package org.javagram.api.phone.base.call;

import org.javagram.api.phone.base.TLPhoneCallProtocol;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Requested phone call
 * phoneCallRequested#87eabb53 flags:# video:flags.5?true id:long access_hash:long date:int admin_id:int participant_id:int g_a_hash:bytes protocol:PhoneCallProtocol = PhoneCall;
 */
public class TLPhoneCallRequested extends TLAbsPhoneCall {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x87eabb53;

    private static final int FLAG_VIDEO = 0x00000020; // 5 : Whether this is a video call

    /**
     * Phone call ID
     */
    private long id;
    
    /**
     * Access hash
     */
    private long accessHash;
    
    /**
     * When was the phone call created
     */
    private int date;
    
    /**
     * ID of the creator of the phone call
     */
    private int adminId;
    
    /**
     * ID of the other participant of the phone call
     */
    private int participantId;
    
    /**
     * @see <a href="https://core.telegram.org/api/end-to-end/voice-calls">Parameter for key exchange</a>
     */
    private TLBytes gAHash;
    
    /**
     * Call protocol info to be passed to libtgvoip
     */
    private TLPhoneCallProtocol protocol;

    public TLPhoneCallRequested() {
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

    public TLBytes getgAHash() {
        return gAHash;
    }

    public void setgAHash(TLBytes gAHash) {
        this.gAHash = gAHash;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TLPhoneCallProtocol protocol) {
        this.protocol = protocol;
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
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.adminId, stream);
        StreamingUtils.writeInt(this.participantId, stream);
        StreamingUtils.writeTLBytes(this.gAHash, stream);
        StreamingUtils.writeTLObject(this.protocol, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.adminId = StreamingUtils.readInt(stream);
        this.participantId = StreamingUtils.readInt(stream);
        this.gAHash = StreamingUtils.readTLBytes(stream, context);
        this.protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
    }

    @Override
    public String toString() {
        return "phoneCallRequested#87eabb53";
    }

}
