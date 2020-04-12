package org.telegram.api.peer.base;

import org.telegram.api._primitives.TLObject;

/**
 * The Peer type.
 */
public abstract class TLAbsPeer extends TLObject {

    protected int id = 0;

    protected TLAbsPeer() {
        super();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toLog() {
        return "--UNKNOWN--";
    }

}