package org.javagram.api.account.functions;

import org.javagram.api.peer.base.input.notify.TLAbsInputNotifyPeer;
import org.javagram.api.peer.base.input.notify.TLInputPeerNotifySettings;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLBool;
import org.javagram.api._primitives.TLContext;
import org.javagram.api._primitives.TLMethod;
import org.javagram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Edits notification settings from a given user/group, from all users/all groups.
 * account.updateNotifySettings#84be5b93 peer:InputNotifyPeer settings:InputPeerNotifySettings = Bool;
 */
public class TLRequestAccountUpdateNotifySettings extends TLMethod<TLBool> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x84be5b93;

    /**
     * Notification source
     */
    private TLAbsInputNotifyPeer peer;
    
    /**
     * Notification settings
     */
    private TLInputPeerNotifySettings settings;

    public TLRequestAccountUpdateNotifySettings() {
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

    public TLInputPeerNotifySettings getSettings() {
        return this.settings;
    }

    public void setSettings(TLInputPeerNotifySettings value) {
        this.settings = value;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.settings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsInputNotifyPeer.class);
        this.settings = StreamingUtils.readTLObject(stream, context, TLInputPeerNotifySettings.class);
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
        return "account.updateNotifySettings#84be5b93";
    }

}