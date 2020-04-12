package org.telegram.api.update.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.peer.base.TLFolderPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;

/**
 * The dialog list of a folder was changed
 * updateFolderPeers#19360dc0 folder_peers:Vector&lt;FolderPeer&gt; pts:int pts_count:int = Update;
 */
public class TLUpdateFolderPeers extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x19360dc0;
    
    /**
     * New peer list
     */
    private TLVector<TLFolderPeer> folderPeers;

    /**
     * @see <a href="https://core.telegram.org/api/updates">Event count after generation</a>
     */
    private int pts;
    
    /**
     * @see <a href="https://core.telegram.org/api/updates">Number of events that were generated</a>
     */
    private int ptsCount;
    public TLUpdateFolderPeers() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLFolderPeer> getFolderPeers() {
        return folderPeers;
    }

    public void setFolderPeers(TLVector<TLFolderPeer> folderPeers) {
        this.folderPeers = folderPeers;
    }

    @Override
    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public int getPtsCount() {
        return ptsCount;
    }

    public void setPtsCount(int ptsCount) {
        this.ptsCount = ptsCount;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.folderPeers, stream);
        StreamingUtils.writeInt(this.pts, stream);
        StreamingUtils.writeInt(this.ptsCount, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.folderPeers = StreamingUtils.readTLVector(stream, context, TLFolderPeer.class);
        this.pts = StreamingUtils.readInt(stream);
        this.ptsCount = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateFolderPeers#19360dc0";
    }

}