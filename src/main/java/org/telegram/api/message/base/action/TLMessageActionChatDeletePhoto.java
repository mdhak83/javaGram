package org.telegram.api.message.base.action;

/**
 * Group profile photo removed
 * messageActionChatDeletePhoto#95e3fbef = MessageAction;
 */
public class TLMessageActionChatDeletePhoto extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x95e3fbef;

    public TLMessageActionChatDeletePhoto() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionChatDeletePhoto#95e3fbef";
    }

}