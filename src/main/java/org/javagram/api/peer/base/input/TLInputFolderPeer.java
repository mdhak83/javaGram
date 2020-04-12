package org.javagram.api.peer.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLObject;

/**
 * Peer in a folder
 * inputFolderPeer#fbd2c296 peer:InputPeer folder_id:int = InputFolderPeer;
 */
public class TLInputFolderPeer extends TLObject {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfbd2c296;

    /**
     * Peer
     */
    private TLAbsInputPeer peer;
    
    /**
     * Folder ID
     */
    private int folderId;

    public TLInputFolderPeer() {
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
        return "inputFolderPeer#fbd2c296";
    }

}