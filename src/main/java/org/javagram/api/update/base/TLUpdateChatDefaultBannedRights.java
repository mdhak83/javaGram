package org.javagram.api.update.base;

import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.chat.base.TLChatBannedRights;
import org.javagram.api.peer.base.TLAbsPeer;
import org.javagram.utils.StreamingUtils;

/**
 * Default banned rights in a @see <a href="https://core.telegram.org/api/channel">normal chat</a> were updated
 * updateChatDefaultBannedRights#54c01850 peer:Peer default_banned_rights:ChatBannedRights version:int = Update;
 */
public class TLUpdateChatDefaultBannedRights extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x54c01850;

    /**
     * The chat
     */
    private TLAbsPeer peer;

    /**
     * New default banned rights
     */
    private TLChatBannedRights defaultBannedRights;

    /**
     * Version
     */
    private int version;

    public TLUpdateChatDefaultBannedRights() {
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

    public TLChatBannedRights getDefaultBannedRights() {
        return defaultBannedRights;
    }

    public void setDefaultBannedRights(TLChatBannedRights defaultBannedRights) {
        this.defaultBannedRights = defaultBannedRights;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.defaultBannedRights, stream);
        StreamingUtils.writeInt(this.version, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.defaultBannedRights = StreamingUtils.readTLObject(stream, context, TLChatBannedRights.class);
        this.version = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateChatDefaultBannedRights#54c01850";
    }

}