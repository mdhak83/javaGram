package org.telegram.api.phone.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Protocol info for libtgvoip
 * phoneCallProtocol#a2bb35cb flags:# udp_p2p:flags.0?true udp_reflector:flags.1?true min_layer:int max_layer:int = PhoneCallProtocol;
 */
public class TLPhoneCallProtocol extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa2bb35cb;

    private static final int FLAG_UDP_P2P       = 0x00000001; // 0
    private static final int FLAG_UDP_REFLECTOR = 0x00000002; // 1

    /**
     * Minimum layer for remote libtgvoip
     */
    private int minLayer;
    
    /**
     * Maximum layer for remote libtgvoip
     */
    private int maxLayer;

    public TLPhoneCallProtocol() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getMinLayer() {
        return minLayer;
    }

    public void setMinLayer(int minLayer) {
        this.minLayer = minLayer;
    }

    public int getMaxLayer() {
        return maxLayer;
    }

    public void setMaxLayer(int maxLayer) {
        this.maxLayer = maxLayer;
    }
    
    public void setUDPP2PAllowed(boolean value) {
        this.setFlag(FLAG_UDP_P2P, value);
    }
    
    public void setUDPReflectorAllowed(boolean value) {
        this.setFlag(FLAG_UDP_REFLECTOR, value);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.minLayer, stream);
        StreamingUtils.writeInt(this.maxLayer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.minLayer = StreamingUtils.readInt(stream);
        this.maxLayer = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "phoneCallProtocol#a2bb35cb";
    }

}