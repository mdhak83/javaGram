package org.telegram.api.update.base;

import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.peer.base.dialog.TLAbsDialogPeer;
import org.telegram.utils.StreamingUtils;

/**
 * The manual unread mark of a chat was changed
 * updateDialogUnreadMark#e16459c3 flags:# unread:flags.0?true peer:DialogPeer = Update;
 */
public class TLUpdateDialogUnreadMark extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xe16459c3;

    private static final int FLAG_UNREAD    = 0x00000001; // 0 : Was the chat marked or unmarked as read

    /**
     * The dialog
     */
    private TLAbsDialogPeer peer;

    public TLUpdateDialogUnreadMark() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isUnread() {
        return this.isFlagSet(FLAG_UNREAD);
    }
    
    public void setUnread(boolean value) {
        this.setFlag(FLAG_UNREAD, value);
    }
    
    public TLAbsDialogPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsDialogPeer peer) {
        this.peer = peer;
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
        return "updateDialogUnreadMark#e16459c3";
    }

}