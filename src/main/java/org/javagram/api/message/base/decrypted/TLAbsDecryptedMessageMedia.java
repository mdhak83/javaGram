/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.javagram.api.message.base.decrypted;

import org.javagram.api._primitives.TLObject;

/**
 * Abstract class to describes media contents of an encrypted message
 * @author Ruben Bermudez
 * @version 2.0
 * @since 02/MAY/2015
 */
public abstract class TLAbsDecryptedMessageMedia extends TLObject {

    protected TLAbsDecryptedMessageMedia() {
        super();
    }

}