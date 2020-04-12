package org.javagram.api.message.base.action;

import org.javagram.api._primitives.TLObject;

/**
 * The MessageAction type
 */
public abstract class TLAbsMessageAction extends TLObject {

    protected TLAbsMessageAction() {
        super();
    }

    public String toLog() {
        return "--UNKNOWN--";
    }

}