/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 12/11/14.
 */
package org.javagram.api.sendmessage.base.action;

/**
 * The type TLSendMessageGamePlayAction.
 * @author Ruben Bermudez
 * @version 2.0
 * TLSendMessageGamePlayAction
 * @since 12/NOV/2014
 */
public class TLSendMessageGamePlayAction extends TLAbsSendMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xdd6a8f48;

    public TLSendMessageGamePlayAction() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "sendMessageGamePlayAction#dd6a8f48";
    }

}
