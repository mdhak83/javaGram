package org.telegram.api.update.base;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLVector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.peer.base.dialog.TLAbsDialogPeer;

/**
 * Pinned dialogs were updated
 * updatePinnedDialogs#fa0f3ca2 flags:# folder_id:flags.1?int order:flags.0?Vector&lt;DialogPeer&gt; = Update;
 */
public class TLUpdatePinnedDialogs extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfa0f3ca2;

    private static final int FLAG_ORDER     = 0x00000001; // 0
    private static final int FLAG_FOLDER_ID = 0x00000002; // 1

    /**
     * Flags, @see <a href="https://core.telegram.org/mtproto/TL-combinators#conditional-fields">TL conditional fields</a>
     */
    private int flags;
    
    /**
     * If the dialogs are in a folder, the folder ID
     */
    private int folderId;
    
    /**
     * New order of pinned dialogs
     */
    private TLVector<TLAbsDialogPeer> order;

    public TLUpdatePinnedDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
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

    public TLVector<TLAbsDialogPeer> getOrder() {
        return order;
    }

    public void setOrder(TLVector<TLAbsDialogPeer> order) {
        this.order = order;
        if (this.order != null && !this.order.isEmpty()) {
            this.flags |= FLAG_ORDER;
        } else {
            this.flags &= ~FLAG_ORDER;
        }
    }

    /**
     * @return true if the dialogs are in a folder
     */
    public boolean isInFolder() {
        return this.isFlagSet(FLAG_FOLDER_ID);
    }

    /**
     * @return true if the order of pinned dialogs is new
     */
    public boolean hasOrder() {
        return this.isFlagSet(FLAG_ORDER);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if ((this.flags & FLAG_FOLDER_ID) != 0) {
            StreamingUtils.writeInt(this.folderId, stream);
        }
        if ((this.flags & FLAG_ORDER) != 0) {
            StreamingUtils.writeTLVector(this.order, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if ((this.flags & FLAG_FOLDER_ID) != 0) {
            this.folderId = StreamingUtils.readInt(stream);
        }
        if ((this.flags & FLAG_ORDER) != 0) {
            this.order = StreamingUtils.readTLVector(stream, context, TLAbsDialogPeer.class);
        }
    }

    @Override
    public String toString() {
        return "updatePinnedDialogs#fa0f3ca2";
    }

}