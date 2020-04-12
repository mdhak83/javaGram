package org.telegram.api.messages.functions;

import org.telegram.api.messages.base.TLMessagesChatFull;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import org.telegram.api._primitives.TLMethod;
import org.telegram.api._primitives.TLObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Returns full chat info according to its ID.
 * messages.getFullChat#3b831c66 chat_id:int = messages.ChatFull;
 */
public class TLRequestMessagesGetFullChat extends TLMethod<TLMessagesChatFull> {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x3b831c66;

    /**
     * Chat ID
     */
    private int chatId;

    public TLRequestMessagesGetFullChat() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    public int getChatId() {
        return chatId;
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
    public TLMessagesChatFull deserializeResponse(InputStream stream, TLContext context) throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        } else if (res instanceof TLMessagesChatFull) {
            return (TLMessagesChatFull) res;
        } else {
            throw new IOException("Incorrect response type. Expected " + TLMessagesChatFull.class.getName() + ", got: " + res.getClass().getCanonicalName());
        }
    }

    @Override
    public String toString() {
        return "messages.getFullChat#3b831c66";
    }

}