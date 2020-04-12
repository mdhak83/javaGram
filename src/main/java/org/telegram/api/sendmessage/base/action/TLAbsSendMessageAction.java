package org.telegram.api.sendmessage.base.action;

import org.telegram.api._primitives.TLObject;

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
