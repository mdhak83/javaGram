package org.telegram.api.sendmessage.base.action;

/**
 * The type TL send message cancel action.
 * @author Ruben Bermudez
 * @version 2.0
 * TLSendMessageCancelAction
 * @since 12/NOV/2014
 */
public class TLSendMessageCancelAction extends TLAbsSendMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xfd5ec8f5;

    public TLSendMessageCancelAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "sendMessageCancelAction#fd5ec8f5";
    }

}
