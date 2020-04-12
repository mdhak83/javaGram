package org.javagram.api.sendmessage.base.action;

import org.javagram.api._primitives.TLObject;

/**
 * The SendMessageAction type.
 */
public abstract class TLAbsSendMessageAction extends TLObject {

    protected TLAbsSendMessageAction() {
        super();
    }
    
    public String toLog() {
        return this.getClass().getSimpleName().substring(2);
    }

}
