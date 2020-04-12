package org.telegram.api.update.base;

import org.telegram.api.peer.base.TLPeerSettings;
import org.telegram.api.peer.base.TLAbsPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Settings of a certain peer have changed
 * updatePeerSettings#6a7e7366 peer:Peer settings:PeerSettings = Update;
 */
public class TLUpdatePeerSettings extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6a7e7366;
    
    /**
     * The peer
     */
    private TLAbsPeer peer;
    
    /**
     * Associated peer settings
     */
    private TLPeerSettings settings;

    public TLUpdatePeerSettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public TLAbsPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
    }

    public TLPeerSettings getSettings() {
        return settings;
    }

    public void setSettings(TLPeerSettings settings) {
        this.settings = settings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.settings = StreamingUtils.readTLObject(stream, context, TLPeerSettings.class);
    }

    @Override
    public String toString() {
        return "updatePeerSettings#6a7e7366";
    }

}