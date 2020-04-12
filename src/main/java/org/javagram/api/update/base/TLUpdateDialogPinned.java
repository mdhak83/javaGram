package org.javagram.api.update.base;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.dialog.TLAbsDialogPeer;

/**
 * A dialog was pinned/unpinned
 */
public class TLUpdateDialogPinned extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6e6fe51c;

    private static final int FLAG_PINNED    = 0x00000001; // 0
    private static final int FLAG_FOLDER_ID = 0x00000002; // 1

    /**
     * If the dialog is in a folder, the folder ID
     */
    private int folderId;
    
    /**
     * The dialog
     */
    private TLAbsDialogPeer peer;

    public TLUpdateDialogPinned() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    /**
     * 
     * @return Whether the dialog was pinned
     */
    public boolean isPinned() {
        return this.isFlagSet(FLAG_PINNED);
    }

    /**
     * 
     * @return Whether the dialog is in a folder
     */
    public boolean isInFolder() {
        return this.isFlagSet(FLAG_FOLDER_ID);
    }

    public TLAbsDialogPeer getPeer() {
        return peer;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsDialogPeer.class);
    }

    @Override
    public String toString() {
        return "updateDialogPinned#6e6fe51c";
    }
    
}