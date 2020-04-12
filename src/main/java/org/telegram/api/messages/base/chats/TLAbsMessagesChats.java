package org.telegram.api.messages.base.chats;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

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
