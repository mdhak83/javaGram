package org.javagram.api.update.base.encrypted;

import org.javagram.api.chat.base.encrypted.TLAbsEncryptedChat;
import org.javagram.api.update.base.TLAbsUpdate;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL update encryption.
 */
public class TLUpdateEncryption extends TLAbsUpdate {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0xb4a2e88d;

    private TLAbsEncryptedChat chat;
    private int date;

    public TLUpdateEncryption() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    /**
     * Gets chat.
     *
     * @return the chat
     */
    public TLAbsEncryptedChat getChat() {
        return this.chat;
    }

    /**
     * Sets chat.
     *
     * @param chat the chat
     */
    public void setChat(TLAbsEncryptedChat chat) {
        this.chat = chat;
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
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLObject(this.chat, stream);
        StreamingUtils.writeInt(this.date, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chat = StreamingUtils.readTLObject(stream, context, TLAbsEncryptedChat.class);
        this.date = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "updateEncryption#b4a2e88d";
    }

}