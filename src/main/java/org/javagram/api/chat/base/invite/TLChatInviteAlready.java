package org.javagram.api.chat.base.invite;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The user has already joined this chat
 * chatInviteAlready#5a686d7c chat:Chat = ChatInvite;
 */
public class TLChatInviteAlready extends TLAbsChatInvite {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x5a686d7c;

    /**
     * The chat connected to the invite
     */
    private TLAbsChat chat;

    public TLChatInviteAlready() {
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
    public TLAbsChat getChat() {
        return this.chat;
    }

    /**
     * Sets chat.
     *
     * @param chat the chat
     */
    public void setChat(TLAbsChat chat) {
        this.chat = chat;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        super.serializeBody(stream);
        StreamingUtils.writeTLObject(this.chat, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        super.deserializeBody(stream, context);
        this.chat = StreamingUtils.readTLObject(stream, context, TLAbsChat.class);
    }

    @Override
    public String toString() {
        return "chat.chatInviteAlready#5a686d7c";
    }

}