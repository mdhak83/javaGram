package org.telegram.api.chat.base;

import org.telegram.api._primitives.TLObject;

/**
 * The ChatFull type;
 * Object containing detailed group info (channel or chat)
 */
public abstract class TLAbsChatFull extends TLObject {

    /**
     * ID of the group
     */
    protected int id;

    /**
     * Info about the group
     */
    protected String about;

    protected TLAbsChatFull() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}