package org.telegram.api.message.base.action;

/**
 * Empty constructor.
 * messageActionEmpty#b6aef7b0 = MessageAction;
 */
public class TLMessageActionEmpty extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb6aef7b0;

    public TLMessageActionEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionEmpty#b6aef7b0";
    }

}