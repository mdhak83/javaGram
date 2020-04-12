package org.javagram.api.message.base.decrypted;

/**
 * The type TL decrypted message action noop.
 */
public class TLDecryptedMessageActionNoop extends TLDecryptedMessageAction {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xa82fdd63;

    public TLDecryptedMessageActionNoop() {
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public String toString() {
        return "decryptedMessageActionNoop#a82fdd63";
    }

}