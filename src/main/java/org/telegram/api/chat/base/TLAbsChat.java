package org.telegram.api.chat.base;

import org.telegram.api.chat.base.photo.TLChatPhoto;
import org.telegram.utils.BotLogger;
import org.telegram.api._primitives.TLObject;

public abstract class TLAbsChat extends TLObject {

    protected int id;
    protected Long accessHash;

    protected TLAbsChat() {
        super();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Long getAccessHash() {
        return this.accessHash;
    }
    
    public void setAccessHash(Long accessHash) {
        if ((this.accessHash == null || this.accessHash == 0L) && accessHash != null && accessHash != 0L) {
            BotLogger.info("CHANNEL", "AccessHash modified from : " + this.toLog());
            this.accessHash = accessHash;
            BotLogger.info("CHANNEL", "AccessHash modified to : " + this.toLog());
        }
    }
    
    public abstract String getTitle();
    public abstract void setTitle(String title);
    
    public boolean isChannel() {
        return false;
    }
    
    public TLChatPhoto getLogo() {
        return null;
    }

    public String toLog() {
        return "--UNKNOWN--";
    }
    
}