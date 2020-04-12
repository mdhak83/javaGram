package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import org.telegram.api.peer.base.dialog.TLAbsDialogPeer;
import org.telegram.api._primitives.TLVector;

/**
 * Get dialogs manually marked as unread
 * messages.getDialogUnreadMarks#22e24e22 = Vector&lt;DialogPeer&gt;;
 */
public class TLRequestMessagesGetDialogUnreadMarks extends TLMethod<TLVector<TLAbsDialogPeer>> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x22e24e22;

    public TLRequestMessagesGetDialogUnreadMarks() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public TLVector<TLAbsDialogPeer> deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLVector) {
            return (TLVector<TLAbsDialogPeer>) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getDialogUnreadMarks#22e24e22";
    }

}