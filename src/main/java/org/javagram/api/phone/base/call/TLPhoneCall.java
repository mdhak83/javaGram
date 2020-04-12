package org.javagram.api.phone.base.call;

import org.javagram.api.phone.base.TLPhoneCallProtocol;
import org.javagram.api.phone.base.TLPhoneConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLBytes;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLVector;
import org.javagram.utils.StreamingUtils;

/**
 * Phone call
 * phoneCall#8742ae7f flags:# p2p_allowed:flags.5?true id:long access_hash:long date:int admin_id:int participant_id:int g_a_or_b:bytes key_fingerprint:long protocol:PhoneCallProtocol connections:Vector&lt;PhoneConnection&gt; start_date:int = PhoneCall;
 */
public class TLPhoneCall extends TLAbsPhoneCall {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8742ae7f;

    private static final int FLAG_P2P_ALLOWED = 0x00000020; // 5 : Whether P2P connection to the other peer is allowed
    
    /**
     * Call ID
     */
    private long id;
    
    /**
     * Access hash
     */
    private long accessHash;
    
    /**
     * Date of creation of the call
     */
    private int date;
    
    /**
     * User ID of the creator of the call
     */
    private int adminId;
    
    /**
     * ID of the other participant of the phone call
     */
    private int participantId;
    
    /**
     * @see <a href="https://core.telegram.org/api/end-to-end/voice-calls">Parameter for key exchange</a>
     */
    private TLBytes gAorB;

    /**
     * @see <a href="https://core.telegram.org/api/end-to-end/voice-calls">Key fingerprint</a>
     */
    private long keyFingerprint;
    
    /**
     * Call protocol info to be passed to libtgvoip
     */
    private TLPhoneCallProtocol protocol;

    /**
     * List of endpoints the user can connect to to exchange call data
     */
    private TLVector<TLPhoneConnection> connections;
    
    /**
     * When was the call actually started
     */
    private int startDate;

    public TLPhoneCall() {
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

    public TLBytes getgAorB() {
        return gAorB;
    }

    public void setgAorB(TLBytes gAorB) {
        this.gAorB = gAorB;
    }

    public long getKeyFingerprint() {
        return keyFingerprint;
    }

    public void setKeyFingerprint(long keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }

    public TLPhoneCallProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TLPhoneCallProtocol protocol) {
        this.protocol = protocol;
    }

    public TLVector<TLPhoneConnection> getConnections() {
        return connections;
    }

    public void setConnections(TLVector<TLPhoneConnection> connections) {
        this.connections = connections;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return True if P2P connection to the other peer is allowed
     */
    public boolean isP2PAllowed() {
        return this.isFlagSet(FLAG_P2P_ALLOWED);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeLong(this.accessHash, stream);
        StreamingUtils.writeInt(this.date, stream);
        StreamingUtils.writeInt(this.adminId, stream);
        StreamingUtils.writeInt(this.participantId, stream);
        StreamingUtils.writeTLBytes(this.gAorB, stream);
        StreamingUtils.writeLong(this.keyFingerprint, stream);
        StreamingUtils.writeTLObject(this.protocol, stream);
        StreamingUtils.writeTLVector(this.connections, stream);
        StreamingUtils.writeInt(this.startDate, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.id = StreamingUtils.readLong(stream);
        this.accessHash = StreamingUtils.readLong(stream);
        this.date = StreamingUtils.readInt(stream);
        this.adminId = StreamingUtils.readInt(stream);
        this.participantId = StreamingUtils.readInt(stream);
        this.gAorB = StreamingUtils.readTLBytes(stream, context);
        this.keyFingerprint = StreamingUtils.readLong(stream);
        this.protocol = StreamingUtils.readTLObject(stream, context, TLPhoneCallProtocol.class);
        this.connections = StreamingUtils.readTLVector(stream, context, TLPhoneConnection.class);
        this.startDate = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "phoneCall#8742ae7f";
    }

}