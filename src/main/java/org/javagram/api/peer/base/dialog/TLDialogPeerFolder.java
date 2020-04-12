package org.javagram.api.peer.base.dialog;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Folder
 * dialogPeerFolder#514519e2 folder_id:int = DialogPeer;
 */
public class TLDialogPeerFolder extends TLAbsDialogPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x514519e2;

    /**
     * Folder ID
     */
    private int folderId;

    public TLDialogPeerFolder() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.folderId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.folderId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "dialogPeerFolder#514519e2";
    }
    
}