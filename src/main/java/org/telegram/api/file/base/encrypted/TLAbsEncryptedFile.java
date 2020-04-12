/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.file.base.encrypted;

import org.telegram.api._primitives.TLObject;

/**
 * Abstract class that represent a file sent to a encrypted chat
 * @author Ruben Bermudez
 * @version 2.0
 * @since 11/APR/2015
 */
public abstract class TLAbsEncryptedFile extends TLObject {

    protected long id; ///< File id

    protected TLAbsEncryptedFile() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

}