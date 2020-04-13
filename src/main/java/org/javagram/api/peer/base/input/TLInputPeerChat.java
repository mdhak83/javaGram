package org.javagram.api.peer.base.input;

import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Defines a chat for further interaction.
 * inputPeerChat#179be863 chat_id:int = InputPeer;
 */
public class TLInputPeerChat extends TLAbsInputPeer {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x179be863;

    /**
     * Chat identifier
     */
    private int chatId;

    public TLInputPeerChat() {
        super();

    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getId() {
        return this.chatId;
    }

    public int getChatId() {
        return this.chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(this.chatId, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chatId = StreamingUtils.readInt(stream);
    }

    @Override
    public String toString() {
        return "inputPeerChat#179be863";
    }

}