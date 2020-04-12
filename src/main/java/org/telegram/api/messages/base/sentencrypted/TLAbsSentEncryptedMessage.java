package org.telegram.api.messages.base.sentencrypted;

import org.telegram.api.file.base.encrypted.TLAbsEncryptedFile;
import org.telegram.api._primitives.TLObject;

/**
 * The type TL abs sent encrypted message.
 */
public abstract class TLAbsSentEncryptedMessage extends TLObject {
    /**
     * The Date.
     */
    protected int date;
    /**
     * The File.
     */
    protected TLAbsEncryptedFile file;

    protected TLAbsSentEncryptedMessage() {
        super();
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Sets date.
     *
     * @param value the value
     */
    public void setDate(int value) {
        this.date = value;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public TLAbsEncryptedFile getFile() {
        return this.file;
    }

    /**
     * Sets file.
     *
     * @param file the file
     */
    public void setFile(TLAbsEncryptedFile file) {
        this.file = file;
    }

}