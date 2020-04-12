package org.javagram.api.messages.base.chats;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public abstract class TLAbsMessagesChats extends TLObject {
    /**
     * The Chats.
     */
    protected TLVector<TLAbsChat> chats;

    public TLVector<TLAbsChat> getChats() {
        return chats;
    }

}
