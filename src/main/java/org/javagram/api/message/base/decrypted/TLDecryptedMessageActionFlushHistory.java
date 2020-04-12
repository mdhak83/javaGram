package org.javagram.api.message.base.decrypted;

/**
 * The type TL decrypted message action flush history.
 */
public class TLDecryptedMessageActionFlushHistory extends TLDecryptedMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x6719e45c;

    public TLDecryptedMessageActionFlushHistory() {
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "decryptedMessageActionFlushHistory#6719e45c";
    }

}