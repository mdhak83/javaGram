package org.javagram.api.user.base;

import org.javagram.api._primitives.TLObject;

/**
 * The User type.
 * Object defines a user.
 */
public abstract class TLAbsUser extends TLObject {

    protected int id = 0;
    protected Long accessHash = null;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Long getAccessHash() {
        return null;
    }
    
    public void setAccessHash(Long value) {
        if (this.accessHash == null || this.accessHash == 0L) {
            this.accessHash = null;
        }
    }
    
    public boolean isBot() {
        return false;
    }
    
    public String toLog() {
        String ret = null;
        if (this instanceof TLUser) {
            TLUser u = (TLUser) this;
            String n1 = u.getFirstName();
            String n2 = u.getLastName();
            String n3 = u.getUserName();
            if (n1 != null && n1.trim().isEmpty()) {
                n1 = null;
            }
            if (n2 != null && n2.trim().isEmpty()) {
                n2 = null;
            }
            if (n3 != null && n3.trim().isEmpty()) {
                n3 = null;
            }
            ret = (n1 != null ? n1 : "---") + "/";
            ret += (n2 != null ? n2 : "---") + "/";
            ret += (n3 != null ? n3 : "---");
            ret += " (User;" + u.id + ")";
        }
        return ret;
    }

}