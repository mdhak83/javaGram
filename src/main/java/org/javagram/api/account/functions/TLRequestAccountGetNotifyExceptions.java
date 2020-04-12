package org.javagram.api.account.functions;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.javagram.api.peer.base.input.notify.TLAbsInputNotifyPeer;
import org.javagram.api.updates.base.TLAbsUpdates;

/**
 * Returns list of chats with non-default notification settings
 * account.getNotifyExceptions#53577479 flags:# compare_sound:flags.1?true peer:flags.0?InputNotifyPeer = Updates;
 */
public class TLRequestAccountGetNotifyExceptions extends TLMethod<TLAbsUpdates> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x53577479;

    private static final int FLAG_PEER          = 0x00000001; // 0 : If specified, only chats of the specified category will be returned
    private static final int FLAG_COMPARE_SOUND = 0x00000002; // 1 : If true, chats with non-default sound will also be returned

    /**
     * If specified, only chats of the specified category will be returned
     */
    private TLAbsInputNotifyPeer peer;

    public TLRequestAccountGetNotifyExceptions() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }
    
    public boolean isCompareSound() {
        return this.isFlagSet(FLAG_COMPARE_SOUND);
    }

    public void setCompareSound(boolean value) {
        this.setFlag(FLAG_COMPARE_SOUND, value);
    }

    public boolean hasPeer() {
        return this.isFlagSet(FLAG_PEER);
    }

    public TLAbsInputNotifyPeer getPeer() {
        return peer;
    }

    public void setPeer(TLAbsInputNotifyPeer peer) {
        this.peer = peer;
        this.setFlag(FLAG_PEER, this.peer != null);
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.isFlagSet(FLAG_PEER)) {
            StreamingUtils.writeTLObject(this.peer, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasPeer()) {
            this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputNotifyPeer.class);
        }
    }

    @Override
    public TLAbsUpdates deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsUpdates) {
            return (TLAbsUpdates) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getNotifyExceptions#53577479";
    }

}