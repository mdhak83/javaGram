package org.javagram.api.update.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.TLAbsPeer;

/**
 * Incoming messages were read
 */
public class TLUpdateReadHistoryInbox extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9c974fdf;

    private static final int FLAG_FOLDER_ID = 0x00000001; // 0 : Folder ID

    /**
     * Folder ID
     */
    private int folderId;
    
    /**
     * Peer
     */
    private TLAbsPeer peer;
    
    /**
     * Maximum ID of messages read
     */
    private int maxId;
    
    /**
     * Number of messages that are still unread
     */
    private int stillUnreadCount;
    
    /**
     * 	@see <a href="https://core.telegram.org/api/updates">Event count after generation</a>
     */
    private int pts;
    
    /**
     * 	@see <a href="https://core.telegram.org/api/updates">Number of events that were generated</a>
     */
    private int ptsCount;

    public TLUpdateReadHistoryInbox() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasFolderID() {
        return this.isFlagSet(FLAG_FOLDER_ID);
    }
    
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
        if (this.folderId != 0) {
            this.flags |= FLAG_FOLDER_ID;
        } else {
            this.flags &= ~FLAG_FOLDER_ID;
        }
    }

    public TLAbsPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsPeer peer) {
        this.peer = peer;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getStillUnreadCount() {
        return stillUnreadCount;
    }

    public void setStillUnreadCount(int stillUnreadCount) {
        this.stillUnreadCount = stillUnreadCount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasFolderID()) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.maxId, stream);
        StreamingUtils.writeInt(this.stillUnreadCount, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasFolderID()) {
            this.folderId = StreamingUtils.readInt(stream);
        }
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsPeer.class);
        this.maxId = StreamingUtils.readInt(stream);
        this.stillUnreadCount = StreamingUtils.readInt(stream);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateReadHistoryInbox#9c974fdf";
    }

}