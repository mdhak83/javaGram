/*
 * This is the source code of Telegram Bot v. 2.0
 * It is licensed under GNU GPL v. 3 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Ruben Bermudez, 13/11/14.
 */
package org.telegram.api.file.base.encrypted;

/**
 * Empty file sent to a encrypted chat
 * @author Ruben Bermudez
 * @version 2.0
 * @since 11/APR/2015
 */
public class TLEncryptedFileEmpty extends TLAbsEncryptedFile {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xc21f497e;

    public TLEncryptedFileEmpty() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "encryptedFileEmpty#c21f497e";
    }

}