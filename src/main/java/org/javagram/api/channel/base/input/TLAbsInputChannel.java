package org.javagram.api.channel.base.input;

import org.javagram.api._primitives.TLObject;

/**
 * InputChannel type.
 */
public abstract class TLAbsInputChannel extends TLObject{
    
    protected TLAbsInputChannel() {
        super();
    }
    
    public String toLog() {
        return "--UNKNOWN--";
    }
    
}
