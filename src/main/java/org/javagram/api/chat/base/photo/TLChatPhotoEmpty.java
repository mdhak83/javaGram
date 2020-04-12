/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.javagram.api.chat.base.photo;

/**
 * Represent an empty chat photo
 * @author Ruben Bermudez
 * @version 2.0
 * @since 02/MAY/2015
 */
public class TLChatPhotoEmpty extends TLAbsChatPhoto {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x37c1011c;

    public TLChatPhotoEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "chatPhotoEmpty#37c1011c";
    }

}