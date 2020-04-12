package org.telegram.api.peer.base.notify.settings;

import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * peerNotifySettings
 * Notification settings.
 * peerNotifySettings#af509d20 flags:# show_previews:flags.0?Bool silent:flags.1?Bool mute_until:flags.2?int sound:flags.3?string = PeerNotifySettings;
 */
public class TLPeerNotifySettings extends TLAbsPeerNotifySettings {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xaf509d20;

    private static final int FLAG_SHOW_PREVIEWS = 0x00000001; // 0 : Display text in notifications
    private static final int FLAG_SILENT        = 0x00000002; // 1 : Mute peer?
    private static final int FLAG_MUTE_UNTIL    = 0x00000004; // 2 : Mute all notifications until this date
    private static final int FLAG_SOUND         = 0x00000008; // 3 : Audio file name for notifications

    /**
     * Display text in notifications
     */
    private Boolean showPreviews;
    
    /**
     * Mute peer?
     */
    private Boolean silent;
    
    /**
     * Mute all notifications until this date
     */
    private int muteUntil;
    
    /**
     * Audio file name for notifications
     */
    private String sound;

    public TLPeerNotifySettings() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public boolean hasShowPreviews() {
        return this.isFlagSet(FLAG_SHOW_PREVIEWS);
    }
    
    public Boolean isShowPreviews() {
        return this.showPreviews;
    }
    
    public void setShowPreviews(boolean value) {
        this.showPreviews = value;
        this.setFlag(FLAG_SHOW_PREVIEWS, value);
    }
    
    public boolean hasSilent() {
        return this.isFlagSet(FLAG_SILENT);
    }
    
    public Boolean isSilent() {
        return this.silent;
    }
    
    public void setSilent(boolean value) {
        this.silent = value;
        this.setFlag(FLAG_SILENT, value);
    }

    public boolean hasMuteUntil() {
        return this.isFlagSet(FLAG_MUTE_UNTIL);
    }
    
    public int getMuteUntil() {
        return this.muteUntil;
    }

    public void setMuteUntil(int muteUntil) {
        this.muteUntil = muteUntil;
        this.setFlag(FLAG_MUTE_UNTIL, true);
    }

    public boolean hasSound() {
        return this.isFlagSet(FLAG_SOUND);
    }
    
    public String getSound() {
        return this.sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
        this.setFlag(FLAG_SOUND, sound != null && !sound.trim().isEmpty());
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.flags, stream);
        if (this.hasShowPreviews()) {
            StreamingUtils.writeTLBool(this.showPreviews, stream);
        }
        if (this.hasSilent()) {
            StreamingUtils.writeTLBool(this.silent, stream);
        }
        if (this.hasMuteUntil()) {
            StreamingUtils.writeInt(this.muteUntil, stream);
        }
        if (this.hasSound()) {
            StreamingUtils.writeTLString(this.sound, stream);
        }
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.flags = StreamingUtils.readInt(stream);
        if (this.hasShowPreviews()) {
            this.showPreviews = StreamingUtils.readTLBool(stream);
        }
        if (this.hasSilent()) {
            this.silent = StreamingUtils.readTLBool(stream);
        }
        if (this.hasMuteUntil()) {
            this.muteUntil = StreamingUtils.readInt(stream);
        }
        if (this.hasSound()) {
            this.sound = StreamingUtils.readTLString(stream);
        }
    }

    @Override
    public String toString() {
        return "peerNotifySettings#af509d20";
    }

}