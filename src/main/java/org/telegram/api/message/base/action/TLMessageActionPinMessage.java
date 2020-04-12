package org.telegram.api.message.base.action;

/**
 * A message was pinned
 * messageActionPinMessage#94bd38ed = MessageAction;
 */
public class TLMessageActionPinMessage extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x94bd38ed;

    public TLMessageActionPinMessage() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionPinMessage#94bd38ed";
    }

}