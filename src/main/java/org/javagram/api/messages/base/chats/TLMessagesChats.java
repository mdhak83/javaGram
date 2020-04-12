package org.javagram.api.messages.base.chats;

import org.javagram.api.chat.base.TLAbsChat;
import org.javagram.utils.StreamingUtils;
import org.javagram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages chats.
 */
public class TLMessagesChats extends TLAbsMessagesChats {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x64ff9fd5;

    public TLMessagesChats() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.chats, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
    }

    @Override
    public String toString() {
        return "messages.chats#64ff9fd5";
    }

}