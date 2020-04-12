package org.telegram.api.dialog.base.input;

import org.telegram.api.message.base.input.TLAbsInputMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;

/**
 * All peers in a folder
 * inputDialogPeerFolder#64600527 folder_id:int = InputDialogPeer;
 */
public class TLInputDialogPeerFolder extends TLAbsInputMessage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x64600527;
    
    /**
     * Folder ID
     */
    private int folderId;

    public TLInputDialogPeerFolder() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFolderID() {
        return folderId;
    }

    public void setFolderID(int folderId) {
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
        return "inputDialogPeerFolder#64600527";
    }

}