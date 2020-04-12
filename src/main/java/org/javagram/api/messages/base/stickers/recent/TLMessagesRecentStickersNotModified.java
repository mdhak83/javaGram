package org.javagram.api.messages.base.stickers.recent;

import org.javagram.api._primitives.TLObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 *  * @since 07/AUG/2016
 */
public class TLMessagesRecentStickersNotModified extends TLObject {
    public static final int CLASS_ID = 0xb17f890;

    public TLMessagesRecentStickersNotModified() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "messages.recentStickersNotModified#b17f890";
    }

}
