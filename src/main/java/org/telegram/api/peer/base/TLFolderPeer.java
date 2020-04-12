package org.telegram.api.peer.base;

import org.telegram.api.peer.base.input.TLAbsInputPeer;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api._primitives.TLObject;

/**
 * Peer in a folder
 * folderPeer#e9baa668 peer:Peer folder_id:int = FolderPeer;
 */
public class TLFolderPeer extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe9baa668;

    /**
     * Folder peer info
     */
    private TLAbsInputPeer peer;
    
    /**
     * Folder ID
     */
    private int folderId;

    public TLFolderPeer() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputPeer peer) {
        this.peer = peer;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
    

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeInt(this.folderId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputPeer.class);
        this.folderId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "folderPeer#e9baa668";
    }

}