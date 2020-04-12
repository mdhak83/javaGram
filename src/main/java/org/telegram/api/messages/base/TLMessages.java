package org.telegram.api.messages.base;

import org.telegram.api.chat.base.TLAbsChat;
import org.telegram.api.message.base.TLAbsMessage;
import org.telegram.api.user.base.TLAbsUser;
import org.telegram.utils.StreamingUtils;
import org.telegram.api._primitives.TLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Full list of messages with auxilary data.
 * messages.messages#8c718e87 messages:Vector&lt;Message&gt; chats:Vector&lt;Chat&gt; users:Vector&lt;User&gt; = messages.Messages;
 */
public class TLMessages extends TLAbsMessages {

    /**
     * 
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8c718e87;

    public TLMessages() {
        super();
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void serializeBody(OutputStream stream) throws IOException {
        StreamingUtils.writeTLVector(this.messages, stream);
        StreamingUtils.writeTLVector(this.chats, stream);
        StreamingUtils.writeTLVector(this.users, stream);
    }

    @Override
    public void deserializeBody(InputStream stream, TLContext context) throws IOException {
        this.messages = StreamingUtils.readTLVector(stream, context, TLAbsMessage.class);
        this.chats = StreamingUtils.readTLVector(stream, context, TLAbsChat.class);
        this.users = StreamingUtils.readTLVector(stream, context, TLAbsUser.class);
    }

    @Override
    public String toString() {
        return "messages.messages#8c718e87";
    }
    
}