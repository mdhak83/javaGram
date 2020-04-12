/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.javagram.api.messages.base.stickers;

/**
 * The type TL stickers not modified.
 * @author Ruben Bermudez
 * @version 2.0
 * TLStickersNotModified
 * @since 09/JAN/2015
 */
public class TLStickersNotModified extends TLAbsStickers {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xf1749a22;

    public TLStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.stickersNotModified#f1749a22";
    }

}
