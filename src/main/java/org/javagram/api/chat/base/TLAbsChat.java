package org.javagram.api.chat.base;

import org.javagram.api.chat.base.photo.TLChatPhoto;
import org.javagram.utils.BotLogger;
import org.javagram.api._primitives.TLObject;

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
    
    public abstract Long getAccessHash();

    public boolean isMin() {
        return false;
    }
    
    public void setAccessHash(Long accessHash) {
        BotLogger.info("CHANNEL", "AccessHash modified from : " + this.toLog());
        this.accessHash = accessHash;
        BotLogger.info("CHANNEL", "AccessHash modified to : " + this.toLog());
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