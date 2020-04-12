/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.javagram.api.notify.base.peer;

/**
 * The type TL notify chats.
 * @author Ruben Bermudez
 * @version 2.0
 * TLNotifyChats
 ** @since 13/NOV/2014
 */
public class TLNotifyChats extends TLAbsNotifyPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc007cec3;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "notifyChats#c007cec3";
    }

}
