package org.telegram.api.account.functions;

import org.telegram.api.peer.base.input.notify.TLAbsInputNotifyPeer;
import org.telegram.api.peer.base.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Gets current notification settings for a given user/group, from all users/all groups.
 * account.getNotifySettings#12b3ad31 peer:InputNotifyPeer = PeerNotifySettings;
 */
public class TLRequestAccountGetNotifySettings extends TLMethod<TLAbsPeerNotifySettings> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x12b3ad31;

    /**
     * Notification source
     */
    private TLAbsInputNotifyPeer peer;

    public TLRequestAccountGetNotifySettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public TLAbsInputNotifyPeer getPeer() {
        return this.peer;
    }

    public void setPeer(TLAbsInputNotifyPeer value) {
        this.peer = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputNotifyPeer.class);
    }

    @Override
    public TLAbsPeerNotifySettings deserializeResponse(InputStream stream, TLContext context) throws IOException {
        TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLAbsPeerNotifySettings) {
            return (TLAbsPeerNotifySettings) res;
        } else {
            throw new IOException("Incorrect response type. Expected org.telegram.api.peer.notify.settings.TLAbsPeerNotifySettings, got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "account.getNotifySettings#12b3ad31";
    }

}