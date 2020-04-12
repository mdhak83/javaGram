package org.telegram.api.channel.base.input;

import org.telegram.api._primitives.TLObject;

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
