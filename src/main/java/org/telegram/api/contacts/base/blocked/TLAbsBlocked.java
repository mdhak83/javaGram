/**
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.contacts.base.blocked;

import org.telegram.api.contact.base.TLContactBlocked;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.api._primitives.TLObject;
import org.telegram.api._primitives.TLVector;

/**
 * Abstract class to represent blocked users
 * @author Ruben Bermudez
 * @version 2.0
 * @since 02/MAY/2015
 */
public abstract class TLAbsBlocked extends TLObject {

    protected TLVector<TLContactBlocked> blocked; ///< List of blocked users
    protected TLVector<TLAbsUser> users; ///< List of user referenced in blocked users

    protected TLAbsBlocked() {
        super();
    }

    /**
     * Gets blocked.
     *
     * @return the blocked
     */
    public TLVector<TLContactBlocked> getBlocked() {
        return this.blocked;
    }

    /**
     * Sets blocked.
     *
     * @param value the value
     */
    public void setBlocked(TLVector<TLContactBlocked> value) {
        this.blocked = value;
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