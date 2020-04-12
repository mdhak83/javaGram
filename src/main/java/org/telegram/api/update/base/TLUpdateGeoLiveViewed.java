package org.telegram.api.update.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.peer.base.TLAbsPeer;

/**
 * Live geoposition message was viewed
 * updateGeoLiveViewed#871fb939 peer:Peer msg_id:int = Update;
 */
public class TLUpdateGeoLiveViewed extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x871fb939;

    /**
     * The user that viewed the live geoposition
     */
    private TLAbsPeer peer;
    
    /**
     * Message ID of geoposition message
     */
    private int msgId;

    public TLUpdateGeoLiveViewed() {
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
    
    public int getMsgId() {
        return this.msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.msgId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.msgId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateGeoLiveViewed#871fb939";
    }

}