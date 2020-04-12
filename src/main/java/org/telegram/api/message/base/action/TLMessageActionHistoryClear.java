package org.telegram.api.message.base.action;

/**
 * Chat history was cleared
 * messageActionHistoryClear#9fbab604 = MessageAction;
 */
public class TLMessageActionHistoryClear extends TLAbsMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9fbab604;

    public TLMessageActionHistoryClear() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messageActionHistoryClear#9fbab604";
    }

}