/*
 * This is the source code of bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 9/01/15.
 */
package org.javagram.api.webpage.base;

/**
 * The type TL web page empty.
 * @author Ruben Bermudez
 * @version 2.0
 * TLWebPageEmpty
 * @since 09/JAN/2015
 */
public class TLWebPageNotModified extends TLAbsWebPage {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x85849473;

    public TLWebPageNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "webPageNotModified#85849473";
    }

}
