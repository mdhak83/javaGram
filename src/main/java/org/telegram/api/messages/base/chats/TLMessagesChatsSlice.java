package org.telegram.api.messages.base.chats;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The type TL messages chats slice
 */
public class TLMessagesChatsSlice extends TLAbsMessagesChats {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x9cd81144;

    private int count;

    public TLMessagesChatsSlice() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeInt(count, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        count = StreamingUtils.readInt(stream);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
    }

    @Override
    public String toString() {
        return "messages.chatsSlice#9cd81144";
    }

}