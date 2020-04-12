package org.telegram.api.phone.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLBytes;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Identifies an endpoint that can be used to connect to the other user in a phone call
 * phoneConnection#9d4c17c0 id:long ip:string ipv6:string port:int peer_tag:bytes = PhoneConnection;
 */
public class TLPhoneConnection extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9d4c17c0;

    /**
     * Endpoint ID
     */
    private long id;
    
    /**
     * IP address of endpoint
     */
    private String ip;
    
    /**
     * IPv6 address of endpoint
     */
    private String ipv6;
    
    /**
     * Port ID
     */
    private int port;
    
    /**
     * Our peer tag
     */
    private TLBytes peerTag;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLBytes getPeerTag() {
        return peerTag;
    }

    public void setPeerTag(TLBytes peerTag) {
        this.peerTag = peerTag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeLong(this.id, stream);
        StreamingUtils.writeTLString(this.ip, stream);
        StreamingUtils.writeTLString(this.ipv6, stream);
        StreamingUtils.writeInt(this.port, stream);
        StreamingUtils.writeTLBytes(this.peerTag, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.id = StreamingUtils.readLong(stream);
        this.ip = StreamingUtils.readTLString(stream);
        this.ipv6 = StreamingUtils.readTLString(stream);
        this.port = StreamingUtils.readInt(stream);
        this.peerTag = StreamingUtils.readTLBytes(stream, context);
    }

    @Override
    public String toString() {
        return "phoneConnection#9d4c17c0";
    }

}
