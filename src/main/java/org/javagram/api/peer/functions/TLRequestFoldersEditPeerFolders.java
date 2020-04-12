package org.javagram.api.peer.functions;

import org.javagram.api.phone.base.TLPhonePhoneCall;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;
import org.javagram.api.peer.base.input.TLInputFolderPeer;
import org.javagram.api.updates.base.TLAbsUpdates;
import org.javagram.utils.StreamingUtils;

/**
 * Edit peers in folder
 * folders.editPeerFolders#6847d0ab folder_peers:Vector&lt;InputFolderPeer&gt; = Updates;
 */
public class TLRequestFoldersEditPeerFolders extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6847d0ab;

    /**
     * New peer list
     */
    private TLVector<TLInputFolderPeer> folderPeers;

    public TLRequestFoldersEditPeerFolders() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLVector<TLInputFolderPeer> getFolderPeers() {
        return folderPeers;
    }

    public void setFolderPeers(TLVector<TLInputFolderPeer> folderPeers) {
        this.folderPeers = folderPeers;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.folderPeers, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.folderPeers = StreamingUtils.readTLVector(stream, context, TLInputFolderPeer.class);
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLPhonePhoneCall.class.getCanonicalName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "folders.editPeerFolders#6847d0ab";
    }

}