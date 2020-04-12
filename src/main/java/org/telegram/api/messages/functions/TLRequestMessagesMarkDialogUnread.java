package org.telegram.api.messages.functions;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.telegram.api.dialog.base.input.TLAbsInputDialogPeer;
import org.telegram.api._primitives.TLBool;

/**
 * Manually mark dialog as unread
 * messages.markDialogUnread#c286d98f flags:# unread:flags.0?true peer:InputDialogPeer = Bool;
 */
public class TLRequestMessagesMarkDialogUnread extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc286d98f;

    private static final int FLAG_UNREAD    = 0x00000001; //  0 : Mark as unread/read

    /**
     * Dialog
     */
    private TLAbsInputDialogPeer peer;

    public TLRequestMessagesMarkDialogUnread() {
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

    public TLAbsInputDialogPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputDialogPeer peer) {
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
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputDialogPeer.class);
    }

    @Override
    public TLBool deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLBool) {
            return (TLBool) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.tl.TLBool, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.markDialogUnread#c286d98f";
    }

}