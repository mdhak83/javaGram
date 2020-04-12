package org.telegram.api.update.base;

import org.telegram.api.notify.base.peer.TLAbsNotifyPeer;
import org.telegram.api.peer.base.notify.settings.TLAbsPeerNotifySettings;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update notify settings.
 */
public class TLUpdateNotifySettings extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xbec268ef;

    private TLAbsNotifyPeer peer;
    private TLAbsPeerNotifySettings notifySettings;

    public TLUpdateNotifySettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets peer.
     *
     * @return the peer
     */
    public TLAbsNotifyPeer getPeer() {
        return this.peer;
    }

    /**
     * Sets peer.
     *
     * @param peer the peer
     */
    public void setPeer(TLAbsNotifyPeer peer) {
        this.peer = peer;
    }

    /**
     * Gets notify settings.
     *
     * @return the notify settings
     */
    public TLAbsPeerNotifySettings getNotifySettings() {
        return this.notifySettings;
    }

    /**
     * Sets notify settings.
     *
     * @param notifySettings the notify settings
     */
    public void setNotifySettings(TLAbsPeerNotifySettings notifySettings) {
        this.notifySettings = notifySettings;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.peer, stream);
        StreamingUtils.writeTLObject(this.notifySettings, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.peer = StreamingUtils.readTLObject(stream, context, TLAbsNotifyPeer.class);
        this.notifySettings = StreamingUtils.readTLObject(stream, context, TLAbsPeerNotifySettings.class);
    }

    @Override
    public String toString() {
        return "updateNotifySettings#bec268ef";
    }

}