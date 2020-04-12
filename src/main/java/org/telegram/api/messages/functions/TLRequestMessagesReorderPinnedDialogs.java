package org.telegram.api.messages.functions;

import org.telegram.api._primitives.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.dialog.base.input.TLAbsInputDialogPeer;
import org.telegram.utils.StreamingUtils;

/**
 * Reorder pinned dialogs
 * messages.reorderPinnedDialogs#3b1adf37 flags:# force:flags.0?true folder_id:int order:Vector&lt;InputDialogPeer&gt; = Bool;
 */
public class TLRequestMessagesReorderPinnedDialogs extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3b1adf37;

    private static final int FLAG_FORCE   = 0x00000001; // 0 : If set, dialogs pinned server-side but not present in the <code>order</code> field will be unpinned.

    /**
     * Folder ID
     */
    private int folderId;
    
    /**
     * New dialog order
     */
    private TLVector<TLAbsInputDialogPeer> order;

    public TLRequestMessagesReorderPinnedDialogs() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean isForce() {
        return (this.isFlagSet(FLAG_FORCE));
    }

    public void setForce(boolean value) {
        this.setFlag(FLAG_FORCE, value);
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public TLVector<TLAbsInputDialogPeer> getOrder() {
        return order;
    }

    public void setOrder(TLVector<TLAbsInputDialogPeer> order) {
        this.order = order;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        StreamingUtils.writeInt(this.folderId, stream);
        StreamingUtils.writeTLVector(this.order, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        this.folderId = StreamingUtils.readInt(stream);
        this.order = StreamingUtils.readTLVector(stream, context, TLAbsInputDialogPeer.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLBool.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.reorderPinnedDialogs#3b1adf37";
    }

}