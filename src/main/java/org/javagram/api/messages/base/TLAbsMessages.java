package org.javagram.api.messages.base;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.api.message.base.TLAbsMessage;
import org.javagram.api.user.base.TLAbsUser;
import org.javagram.api._primitives.TLObject;
import org.javagram.api._primitives.TLVector;

/**
 * The messages.Messages type
 */
public abstract class TLAbsMessages extends TLObject {

    /**
     * List of messages
     */
    protected TLVector<TLAbsMessage> messages = null;
    
    /**
     * List of chats mentioned in dialogs
     */
    protected TLVector<TLAbsChat> chats = null;
    
    /**
     * List of users mentioned in messages and chats
     */
    protected TLVector<TLAbsUser> users = null;

    protected TLAbsMessages() {
        super();
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public TLVector<TLAbsMessage> getMessages() {
        return this.messages;
    }

    /**
     * Sets messages.
     *
     * @param value the value
     */
    public void setMessages(TLVector<TLAbsMessage> value) {
        this.messages = value;
    }

    /**
     * Gets chats.
     *
     * @return the chats
     */
    public TLVector<TLAbsChat> getChats() {
        return this.chats;
    }

    /**
     * Sets chats.
     *
     * @param value the value
     */
    public void setChats(TLVector<TLAbsChat> value) {
        this.chats = value;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public TLVector<TLAbsUser> getUsers() {
        return this.users;
    }

    /**
     * Sets users.
     *
     * @param value the value
     */
    public void setUsers(TLVector<TLAbsUser> value) {
        this.users = value;
    }

}